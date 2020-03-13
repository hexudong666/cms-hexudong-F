package com.hexudong.cms.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.hexudong.cms.common.CmsConst;
import com.hexudong.cms.dao.ArticleRepository;
import com.hexudong.cms.pojo.Article;
import com.hexudong.cms.pojo.CateGory;
import com.hexudong.cms.pojo.Channel;
import com.hexudong.cms.pojo.Comment;
import com.hexudong.cms.pojo.Link;
import com.hexudong.cms.pojo.Slide;
import com.hexudong.cms.pojo.User;
import com.hexudong.cms.service.ArticleService;
import com.hexudong.cms.service.CollectService;
import com.hexudong.cms.service.CommentService;
import com.hexudong.cms.service.LinkService;
import com.hexudong.cms.service.SlideService;
import com.hexudong.cms.service.UserService;
import com.hexudong.cms.util.HLUtils;

@Controller
public class IndexController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private SlideService slideService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;
	@Resource
	private CollectService collectService;
	@Autowired
	private LinkService linkService;
	@Autowired
	private ArticleRepository resRepository;
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	@Autowired
	RedisTemplate redisTemplate;
	/**
	 * 
	    * @Title: search
	    * @Description: 进行普通查询和高亮查询
	    * @param @param model
	    * @param @param key
	    * @param @param pageNum
	    * @param @param pageSize
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("/search")
	public String search(Model model,String key,@RequestParam(defaultValue="1")int pageNum,@RequestParam(defaultValue="3")int pageSize) {
		//进行普通查询
	//	List<Article> list = resRepository.findByTitle(key);
	//	model.addAttribute("list", list);
		//进行高亮查询
		PageInfo<Article> pageInfo = (PageInfo<Article>) HLUtils.findByHighLight(elasticsearchTemplate, Article.class, pageNum, pageSize, new String[] {"title"}, "id", key);
		model.addAttribute("list", pageInfo.getList());
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("key", key);
		return "index";
		
	}
	/**
	 * @Title: index   
	 * @Description: 首页   
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/")
	private String index(Model model) {
		hot(model, 1);
		return "index";
	}
	/**
	 * @Title: hot   
	 * @Description: 热门分页   
	 * @param: @param model
	 * @param: @param pageNum
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/hot/{pageNum}.html")
	private String hot(Model model,@PathVariable Integer pageNum) {
		//轮播图
		List<Slide> slideList = slideService.getAll();
		//轮播图下面的文章
			PageInfo<Article> pageInfo = articleService.getHotList(pageNum,8);
			model.addAttribute("pageInfo", pageInfo);
		
		//做栏目的频道进行redis优化
		List<Channel> channelList = redisTemplate.opsForList().range("list", 0, -1);
		if(channelList!=null && channelList.size()!= 0) {
			redisTemplate.expire("list", 5, TimeUnit.MINUTES);
			model.addAttribute("channelList", channelList);
			System.err.println("redis导入");
			
		}
		else {
			List<Channel> ss = articleService.getChannelAll();
			redisTemplate.opsForList().leftPushAll("list",ss.toArray());
			model.addAttribute("channelList", ss);
		}
		//redis优化最新文章
		List<Article> ranges = redisTemplate.opsForList().range("newArticle_redis", 0,6);
		if(ranges!=null && ranges.size()!=0) {
			redisTemplate.expire("newArticle_redis", 5, TimeUnit.MINUTES);
			model.addAttribute("newArticleList", ranges);
			System.err.println("redis优化最新文章");
		}else {
			List<Article> newArticleList = articleService.getNewList(6);
			redisTemplate.opsForList().leftPushAll("newArticle_redis",newArticleList.toArray());
			model.addAttribute("newArticleList", newArticleList);
		}
		//最热文章用redis进行优化
		List<Article> range = redisTemplate.opsForList().range("hot", 0,10);
		System.err.println("最热文章是:"+range);
		if(range!=null && range.size()!=0) {
			redisTemplate.expire("hot", 5, TimeUnit.MINUTES);
			model.addAttribute("ArticleList", range);
			System.err.println("redis优化最热文章");
		}else {
			List<Article> srticleList = articleService.getHotList(10);
			redisTemplate.opsForList().leftPushAll("hot",srticleList.toArray());
			model.addAttribute("ArticleList", srticleList);
		}
		//优化友情链接
		List<Article> youqing = redisTemplate.opsForList().range("youqing", 0,-1);
		if(youqing!=null && youqing.size()!=0) {
			redisTemplate.expire("youqing", 5, TimeUnit.MINUTES);
			 model.addAttribute("LinkList", youqing);
			System.err.println("redis优化友情链接");
		}else {
			List<Link> LinkList =  linkService.select();
			redisTemplate.opsForList().leftPushAll("hot_redis",LinkList.toArray());
			 model.addAttribute("LinkList", LinkList);
		}
		//redis优化最新图片
		List<Article> tupian = redisTemplate.opsForList().range("tupian", 0,-1);
		if(tupian!=null && tupian.size()!=0) {
			redisTemplate.expire("newPicture", 5, TimeUnit.MINUTES);
			model.addAttribute("newPicture", tupian);
			System.err.println("redis优化最新图片");
		}else {
			List<Article> newPicture = articleService.getnewPicture(5);
			redisTemplate.opsForList().leftPushAll("tupian",newPicture.toArray());
			model.addAttribute("newPicture", newPicture);
		}
		
		model.addAttribute("slideList", slideList);
		
		
		return "index";
	}
	
	/**
	 * @Title: channel   
	 * @Description: 频道页   
	 * @param: @param model
	 * @param: @param channelId
	 * @param: @param cateId
	 * @param: @param pageNum
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/{channelId}/{cateId}/{pageNum}.html")
	public String channel(Model model,@PathVariable Integer channelId,@PathVariable Integer cateId,@PathVariable Integer pageNum) {
		PageInfo<Article> pageInfo = articleService.getList(channelId,cateId,pageNum,2);
		List<CateGory> cateList = articleService.getCateListByChannelId(channelId);
		Channel channel = articleService.getChannelByChannelId(channelId);
		model.addAttribute("cateList", cateList);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("channel", channel);

		//做栏目的频道进行redis优化
		List<Channel> channelList = redisTemplate.opsForList().range("list", 0, -1);
		if(channelList!=null && channelList.size()!= 0) {
			redisTemplate.expire("list", 5, TimeUnit.MINUTES);
			model.addAttribute("channelList", channelList);
			System.err.println("redis导入");
			
		}
		else {
			List<Channel> ss = articleService.getChannelAll();
			redisTemplate.opsForList().leftPushAll("list",ss.toArray());
			model.addAttribute("channelList", ss);
		}
		//redis优化最新文章
		List<Article> ranges = redisTemplate.opsForList().range("newArticle_redis", 0,6);
		if(ranges!=null && ranges.size()!=0) {
			redisTemplate.expire("newArticle_redis", 5, TimeUnit.MINUTES);
			model.addAttribute("newArticleList", ranges);
			System.err.println("redis优化最新文章");
		}else {
			List<Article> newArticleList = articleService.getNewList(6);
			redisTemplate.opsForList().leftPushAll("newArticle_redis",newArticleList.toArray());
			model.addAttribute("newArticleList", newArticleList);
		}
		//最热文章用redis进行优化
		List<Article> range = redisTemplate.opsForList().range("hot", 0,10);
		System.err.println("最热文章是:"+range);
		if(range!=null && range.size()!=0) {
			redisTemplate.expire("hot", 5, TimeUnit.MINUTES);
			model.addAttribute("ArticleList", range);
			System.err.println("redis优化最热文章");
		}else {
			List<Article> srticleList = articleService.getHotList(10);
			redisTemplate.opsForList().leftPushAll("hot",srticleList.toArray());
			model.addAttribute("ArticleList", srticleList);
		}
		//优化友情链接
		List<Article> youqing = redisTemplate.opsForList().range("youqing", 0,-1);
		if(youqing!=null && youqing.size()!=0) {
			redisTemplate.expire("youqing", 5, TimeUnit.MINUTES);
			 model.addAttribute("LinkList", youqing);
			System.err.println("redis优化友情链接");
		}else {
			List<Link> LinkList =  linkService.select();
			redisTemplate.opsForList().leftPushAll("hot_redis",LinkList.toArray());
			 model.addAttribute("LinkList", LinkList);
		}
		//redis优化最新图片
		List<Article> tupian = redisTemplate.opsForList().range("tupian", 0,-1);
		if(tupian!=null && tupian.size()!=0) {
			redisTemplate.expire("newPicture", 5, TimeUnit.MINUTES);
			model.addAttribute("newPicture", tupian);
			System.err.println("redis优化最新图片");
		}else {
			List<Article> newPicture = articleService.getnewPicture(5);
			redisTemplate.opsForList().leftPushAll("tupian",newPicture.toArray());
			model.addAttribute("newPicture", newPicture);
		}
		
				
		return "index";
	}
	/**
	 * @Title: articleDetail   
	 * @Description: 文章详情页  
	 * @param: @param id
	 * @param: @param model
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/article/detail/{id}.html")
	public String articleDetail (@PathVariable Integer id,Model model,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "3")Integer pageSize,HttpSession session)  {
		Article article = articleService.getById(id);
		System.out.println("文章详情页："+article);
		User user = userService.getById(article.getUser_id());
		System.out.println("文章详情页："+user);
		article.setNickname(user.getNickname());
		model.addAttribute("article", article);
		//添加友情链接
		 List<Link> LinkList =  linkService.select();
		/** 设置文章点击量，若点击量大于20成为热点文章 **/
		 //当前文章的id
		articleService.setHitsAndHot(id);
		//查找相关文章
		List<Article> newArticleList = articleService.select(id);
		
		User us = (User) session.getAttribute(CmsConst.UserSessionKey);
		model.addAttribute("us", us);
		//进行添加评论
		PageInfo<Comment> info = commentService.selectService(page,pageSize,id);
		model.addAttribute("newArticleList", newArticleList);
		model.addAttribute("article", article);
		model.addAttribute("user", user);
		model.addAttribute("id",id);
		model.addAttribute("LinkList", LinkList);
		model.addAttribute("info", info);
		return "article-detail";
	}
	
}

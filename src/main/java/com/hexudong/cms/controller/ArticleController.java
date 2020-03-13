package com.hexudong.cms.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.hexudong.cms.common.ArticleEnum;
import com.hexudong.cms.common.CmsConst;
import com.hexudong.cms.common.JsonResult;
import com.hexudong.cms.pojo.Article;
import com.hexudong.cms.pojo.CateGory;
import com.hexudong.cms.pojo.Channel;
import com.hexudong.cms.pojo.Collect;
import com.hexudong.cms.pojo.User;
import com.hexudong.cms.service.ArticleService;
import com.hexudong.cms.service.CollectService;

@Controller
@RequestMapping("/article/")
public class ArticleController {
	@Resource
	private CollectService collectService;
	@Autowired
	private ArticleService articleService;
	
	/**
	 * @Title: add   
	 * @Description: 跳转到文章编辑页面
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@GetMapping("/add")
	public  String toAdd(Integer id,Model model) {
		
			List<Channel> channelList = articleService.getChannelAll();
			model.addAttribute("channelList", channelList);
			if(id!=null) {
				Article article = articleService.getById(id);
				List<CateGory> cateList = articleService.getCateListByChannelId(article.getChannel_id());
				model.addAttribute("article", article);
				model.addAttribute("cateList", cateList);
			}
			return "article/add";
		
		
	}
	/**
	 * @Title: save   
	 * @Description: 保存文章   
	 * @param: @param article
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@PostMapping("/save")
	public @ResponseBody JsonResult save(Article article,HttpSession session) {
		User userInfo = (User)session.getAttribute(CmsConst.UserSessionKey);
		article.setUser_id(userInfo.getId());
		articleService.save(article);
		return JsonResult.sucess();
	}
	/**
	 * @Title: getCateListByChannelId   
	 * @Description: 根据频道Id查询分类列表  
	 * @param: @param channelId
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	@GetMapping("/getCateListByChannelId")
	public @ResponseBody JsonResult getCateListByChannelId(Integer channelId) {
		List<CateGory> cateList = articleService.getCateListByChannelId(channelId);
		return JsonResult.sucess(cateList);
	}
	
	/**
	 * @Title: articles   
	 * @Description: 文章管理（我的文章）   
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/articles")
	public String articles(Article article,Model model,HttpSession session,
			@RequestParam(value="pageNum",defaultValue="1") Integer pageNum,
			@RequestParam(value="pageSize",defaultValue="2") Integer pageSize) {
		User userInfo = (User)session.getAttribute(CmsConst.UserSessionKey);
		article.setUser_id(userInfo.getId());
		PageInfo<Article> pageInfo = articleService.getPageInfo(article, pageNum, pageSize);
		model.addAttribute("pageInfo", pageInfo);
		return "article/articles";
	}
	/**
	 * @Title: deleteByIds   
	 * @Description: 根据Ids批量删除文章   
	 * @param: @param ids
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	@PostMapping("/deleteByIds")
	public @ResponseBody JsonResult deleteByIds(String ids) {
		articleService.deleteByIds(ids);
		return JsonResult.sucess();
	}
	//查找收藏文件
	@RequestMapping("/cellects")
	public String cellectSelect(Model model,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "4")Integer PageSize,HttpSession session) {
		User user = (User) session.getAttribute(CmsConst.UserSessionKey);
		PageInfo<Collect> info =  collectService.selectCellects(user.getId(),page,PageSize);
		
		model.addAttribute("info",info);
		return "article/cellects";
	}
	@GetMapping("/picture")
	public String show() {
		return "article/picture";
	}
	/**
	    * @Title: addPicture
	    * @Description: 发布图片
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	@RequestMapping("/sspicture")
	@ResponseBody
	public  int addPicture(Article article) {
		System.out.println(article);
		/**
		 * 如果等于空，那就只是图片
		 */
		if(article.getDesc().equals("")) {
			ArticleEnum image = ArticleEnum.IMAGE;
			int id = image.id;
			System.out.println("第一个id值:"+id);
			article.setSid(id);
			articleService.addArticles(article);
		}
		/**
		 * 如果不等于空娜就是文本内容
		 */else {
			 ArticleEnum html = ArticleEnum.HTML;
				int id = html.id;
				System.out.println("第二个id值:"+id);
			 article.setSid(id);
			 articleService.addArticle(article);
		 }
		System.out.println("图片的路径是:"+article);
		return 1;
		
	}
}

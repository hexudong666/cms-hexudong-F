package com.hexudong.cms.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hexudong.cms.dao.ArticleMapper;
import com.hexudong.cms.dao.ArticleRepository;
import com.hexudong.cms.dao.CateGoryMapper;
import com.hexudong.cms.dao.ChannelMapper;
import com.hexudong.cms.dao.UserMapper;
import com.hexudong.cms.pojo.Article;
import com.hexudong.cms.pojo.CateGory;
import com.hexudong.cms.pojo.Channel;
import com.hexudong.cms.pojo.User;
import com.hexudong.cms.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService{
	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private ChannelMapper channelMapper;
	@Autowired
	private CateGoryMapper cateGoryMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;
	@Autowired
	ArticleRepository repository;
	@Override
	public List<Channel> getChannelAll() {
		return channelMapper.select(null);
	}

	@Override
	public List<CateGory> getCateListByChannelId(Integer channelId) {
		CateGory CateGory = new CateGory();
		CateGory.setChannel_id(channelId);
		return cateGoryMapper.select(CateGory);
	}

	@Override
	public Article getById(Integer id) {
		return articleMapper.selectById(id);
	}

	@Override
	public boolean save(Article article) {
		
		if(article.getId()==null) {
			/** 设置默认值 **/
			article.setHits(0);
			article.setHot(0);
			article.setDeleted(0);
			article.setTousuCnt(0);
			article.setCommentCnt(0);
			article.setCreated(new Date());
			article.setUpdated(new Date());
			/** 新增 **/
			articleMapper.insert(article);
		}else {
			/** 修改 **/
			article.setUpdated(new Date());
			Article a = articleMapper.selectById(article.getId());
			a.setTitle(article.getTitle());
			a.setPicture(article.getPicture());
			a.setChannel_id(article.getChannel_id());
			a.setCategory_id(article.getCategory_id());
			a.setContent(article.getContent());
			a.setStatus(article.getStatus());
			articleMapper.update(a);
		}
		return true;
	}

	@Override
	public PageInfo<Article> getPageInfo(Article article, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Article> articleList = articleMapper.select(article);
		/** 设置频道和分类的名称 **/
		articleList.forEach(a->{
			Channel channel = channelMapper.selectById(a.getChannel_id());
			a.setChannel_name(channel.getName());
			CateGory cate = cateGoryMapper.selectById(a.getCategory_id());
			a.setCategory_name(cate.getName());
			User user = userMapper.selectById(a.getUser_id());
			a.setNickname(user.getNickname());
		});
		return new PageInfo<>(articleList);
	}

	@Override
	public boolean deleteById(Integer id) {
		Article article = articleMapper.selectById(id);
		article.setDeleted(1);
		return articleMapper.update(article)>0;
	}
	@Override
	public boolean deleteByIds(String ids) {
		String[] idArr = ids.split(",");
		for(String id:idArr) {
			deleteById(Integer.parseInt(id));
		}
		return true;
	}

	@Override
	public PageInfo<Article> getHotList(int pageNum, int pageSize) {
		Article article = new Article();
		article.setStatus(1);
		article.setHot(1);
		PageHelper.startPage(pageNum, pageSize);
		List<Article> articleList = articleMapper.select(article);
		articleList.forEach(a->{
			User user = userMapper.selectById(a.getUser_id());
			a.setNickname(user.getNickname());
		});
		return new PageInfo<>(articleList);
	}

	@Override
	public PageInfo<Article> getList(Integer channelId, Integer cateId, Integer pageNum, Integer pageSize) {
		Article article = new Article();
		article.setStatus(1);
		article.setChannel_id(channelId);
		if(cateId>0) {
			article.setCategory_id(cateId);
		}
		PageHelper.startPage(pageNum, pageSize);
		List<Article> articleList = articleMapper.select(article);
		articleList.forEach(a->{
			User user = userMapper.selectById(a.getUser_id());
			a.setNickname(user.getNickname());
		});
		return new PageInfo<>(articleList);
	}

	@Override
	public Channel getChannelByChannelId(Integer channelId) {
		return channelMapper.selectById(channelId);
	}

	//审核文章
	@Override
	public boolean check(Article article) {
		Article article2 = articleMapper.selectById(article.getId());
		article2.setStatus(article.getStatus());
			//利用卡夫卡到消费者进行数据修改
			ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send("hexudong_cms","审核"+"="+article.getId()+"="+article.getStatus());
		return true;
	}

	//进行热点文章
	@Override
	public void setHitsAndHot(Integer id) {
		Article article = articleMapper.selectById(id);
		article.setHits(article.getHits()+1);
		if(article.getHits()>=20) {
			article.setHot(1);
		}
		articleMapper.update(article);
	}

	@Override
	public List<Article> getNewList(Integer pageSize) {
		PageHelper.startPage(1, pageSize);
		Article article = new Article();
		article.setStatus(1);
		return articleMapper.select(article);
	}

	//查找相关文章
	@Override
	public List<Article> select(Integer id) {
		//先查找里面频道id
		Article article = articleMapper.selectById(id);
		System.out.println(article);
		return articleMapper.selects(id,article.getChannel_id());
	}

	/**
	 * 查找最热文章
	 */
	@Override
	public List<Article> getHotList(Integer pageSize) {
		PageHelper.startPage(1, pageSize);
		List<Article> list = articleMapper.gethotselect();
		for (Article article : list) {
			System.out.println("查找到的"+article);
		}
		return list;
	}

	//加文章
	@Override
	public void addArticle(Article article) {
		
		articleMapper.addArticle(article);
	}
	//只是图片
	@Override
	public void addArticles(Article article) {
		PageHelper.startPage(1, 5);
		articleMapper.addArticles(article);
		
	}

	@Override
	public List<Article> getnewPicture(int i) {
		PageHelper.startPage(1, i);
		List<Article> list = articleMapper.selectPicture();
		return list;
	}

}

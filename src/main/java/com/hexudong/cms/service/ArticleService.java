package com.hexudong.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hexudong.cms.pojo.Article;
import com.hexudong.cms.pojo.CateGory;
import com.hexudong.cms.pojo.Channel;

public interface ArticleService {
	/**
	 * @Title: getChannelAll   
	 * @Description: 查询所有频道   
	 * @param: @return      
	 * @return: List<Channel>      
	 * @throws
	 */
	List<Channel> getChannelAll();
	/**
	 * @Title: getCateListByChannelId   
	 * @Description: 根据频道Id查询分类列表   
	 * @param: @param channelId
	 * @param: @return      
	 * @return: List<Category>      
	 * @throws
	 */
	List<CateGory> getCateListByChannelId(Integer channelId);
	/**
	 * @Title: getById   
	 * @Description: 根据Id查询文章   
	 * @param: @param id
	 * @param: @return      
	 * @return: Article      
	 * @throws
	 */
	Article getById(Integer id);
	/**
	 * @Title: save   
	 * @Description: 保存文章   
	 * @param: @param article
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	boolean save(Article article);
	/**
	 * @Title: getPageInfo   
	 * @Description: 分页查询文章   
	 * @param: @param article
	 * @param: @param pageNum
	 * @param: @param pageSize
	 * @param: @return      
	 * @return: PageInfo<Article>      
	 * @throws
	 */
	PageInfo<Article> getPageInfo(Article article,Integer pageNum,Integer pageSize);
	/**
	 * @Title: deleteById   
	 * @Description: 根据Id删除文章  
	 * @param: @param id
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	boolean deleteById(Integer id);
	/**
	 * 
	 * @Title: deleteById   
	 * @Description: 根据Ids批量删除   
	 * @param: @param ids      
	 * @return: void      
	 * @throws
	 */
	boolean deleteByIds(String ids);
	/**
	 * @Title: getHotList   
	 * @Description: 按分页查询热点文章   
	 * @param: @param i
	 * @param: @param j
	 * @param: @return      
	 * @return: List<Slide>      
	 * @throws
	 */
	PageInfo<Article> getHotList(int pageNum, int pageSize);
	/**
	 * @Title: getList   
	 * @Description: 根据频道Id和分类Id查询文章（分页）   
	 * @param: @param channelId
	 * @param: @param cateId
	 * @param: @param i
	 * @param: @param pageNum
	 * @param: @return      
	 * @return: PageInfo<Article>      
	 * @throws
	 */
	PageInfo<Article> getList(Integer channelId, Integer cateId, Integer pageNum, Integer pageSize);
	/**
	 * @Title: getChannelByChannelId   
	 * @Description: 根据Id查询频道   
	 * @param: @param channelId
	 * @param: @return      
	 * @return: Channel      
	 * @throws
	 */
	Channel getChannelByChannelId(Integer channelId);
	/**
	 * @Title: check   
	 * @Description: 文章审核  
	 * @param: @param article
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	boolean check(Article article);
	/**
	 * @Title: setHitsAndHot   
	 * @Description: 设置文章点击量，若点击量大于20成为热点文章    
	 * @param: @param id      
	 * @return: void      
	 * @throws
	 */
	void setHitsAndHot(Integer id);
	/**
	 * @Title: getNewList   
	 * @Description: 查询指定条数的最新文章   
	 * @param: @param pageSize
	 * @param: @return      
	 * @return: List<ArticleService>      
	 * @throws
	 */
	List<Article> getNewList(Integer pageSize);
	/**
	 * 查找相关文章
	 * @param id
	 * @return
	 */
	List<Article> select(Integer id);
	/**
	 * 查找最热文章
	 * @param i
	 * @return
	 */
	List<Article> getHotList(Integer pageSize);
	//加文章
	void addArticle(Article article);
	//只是图片
	void addArticles(Article article);
	//查找最新图片
	List<Article> getnewPicture(int i);
}

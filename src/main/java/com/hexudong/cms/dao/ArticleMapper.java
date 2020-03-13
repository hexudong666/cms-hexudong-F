package com.hexudong.cms.dao;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hexudong.cms.pojo.Article;


public interface ArticleMapper extends BaseDao<Article> {

	@Select("select * from cms_article where channel_id=#{ids} and id!=#{id}")
	List<Article> selects(@Param("id")Integer id,@Param("ids")Integer integer);

	@Select("select * from cms_article where hits>=20 ORDER BY hits desc")
	List<Article> gethotselect();

	/* 判断文章是否相同，查找文章的id */
	@Select("select * from cms_article where title=#{text}")
	Article selectctId(String text);
	//添加图片
	@Insert("insert into cms_article(content,picture,created,sid) values(#{desc},#{picture},now(),${sid} )")
	void addArticle(Article article);
	@Insert("insert into cms_article(picture,created,sid) values(#{picture},now(),${sid} )")
	void addArticles(Article article);

	//查找最新图片
	@Select("select * from cms_article where sid=1 ORDER BY created desc ")
	List<Article> selectPicture();
	@Insert("insert into cms_article(content,title,created) values(#{content},#{title},now() )")
	 void add(Article article);


}

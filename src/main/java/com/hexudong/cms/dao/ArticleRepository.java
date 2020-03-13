package com.hexudong.cms.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.hexudong.cms.pojo.Article;


public interface ArticleRepository extends ElasticsearchRepository<Article, Integer> {

	//根据文章标题查找
	List<Article> findByTitle(String key);
}

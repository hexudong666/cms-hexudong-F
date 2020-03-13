package com.hexudong.Test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hexudong.cms.dao.ArticleMapper;
import com.hexudong.cms.dao.ArticleRepository;
import com.hexudong.cms.pojo.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class TestEs {

	@Autowired
	ArticleMapper articleMapper;
	@Autowired
	ArticleRepository articleRepository;
	@Test
	public void show() {
		//从，mysql查找数据
		Article article = new Article();
		article.setStatus(1);
		List<Article> select = articleMapper.select(article);
		//存储到es索引库
		articleRepository.saveAll(select);
		System.out.println("导入成功");
	}
	
}

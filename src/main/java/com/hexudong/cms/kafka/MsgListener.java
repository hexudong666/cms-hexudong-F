package com.hexudong.cms.kafka;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.kafka.listener.MessageListener;

import com.hexudong.cms.dao.ArticleMapper;
import com.hexudong.cms.dao.ArticleRepository;
import com.hexudong.cms.pojo.Article;
import com.hexudong.cms.service.ArticleService;

//消息监听器
public class MsgListener implements MessageListener<String, String> {

	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private ArticleService articleService ;
	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;
	@Autowired
	ArticleRepository repository;
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		
		String value = data.value();
		System.out.println("传过来的value是:"+value);
		if(value.startsWith("审核")) {
			String[] split = value.split("=");
			Article article2 = articleMapper.selectById(Integer.parseInt(split[1]));
			article2.setStatus(Integer.parseInt(split[2]));
			articleMapper.update(article2);

			//必须得先清空里面的数据
			repository.deleteAll();
			Article articles = new Article();
			articles.setStatus(1);
			List<Article> select = articleMapper.select(articles);
			//存储到es索引库
			repository.saveAll(select);
		}
		
	}

}

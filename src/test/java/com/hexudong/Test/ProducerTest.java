package com.hexudong.Test;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.hexudong.cms.dao.ArticleMapper;
import com.hexudong.cms.pojo.Article;
import com.hexudong.utils.FileUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class ProducerTest {

	@Resource
	private ArticleMapper articleMapper;
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	@Test
	public void show() throws IOException {
		//找到这个文件下的所有文件
		File file = new File("D:\\workspace\\articles");
		File[] listFiles = file.listFiles();
		//遍历所有文件
		for (File file2 : listFiles) {
			//拿到文件的名称,作为文章的主题
			String name = file2.getName();
			if(name.contains("?.txt")) {
				String title = name.replace("?.txt", "");
			}
			if(name.contains(".txt")) {
				String title = name.replace(".txt", "");
			}
			String readFile = FileUtil.readFile(file2, "utf8");
			Article article = new Article();
			//注入标题
			article.setTitle(name);
			//文章内容
			article.setContent(readFile);
			 String jsonString = JSON.toJSONString(article); 
			 kafkaTemplate.send("hexudong_cms",jsonString); 
		}
	}
}

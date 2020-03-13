package com.hexudong.Test;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hexudong.utils.FileUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class Spider2Souhu {

	//查询搜狐新闻到本地磁盘(新闻的标题作为文件的名称，新闻的内容作为文件的内容“标题.txt形式保存在本地磁盘”)
	
	@Test
	public void show() throws IOException {
		// TODO Auto-generated method stub
				//connect连接
				Connection connect = Jsoup.connect("http://news.sohu.com/");
				//指定发送的请求方式
				Document document = connect.get();
				//通过请求返回过来html的内容
				/* System.out.println(document); */
				Elements select = document.select(".list16");
				for (Element element : select) {
					//获取标签
					Elements select2 = element.select("a");
					//抓取文章标题
					for (Element a : select2) {
						//获取文章标题
						String title = a.attr("title");
						//获取a标签的进入内容的路径
						String url = a.attr("href");
						System.out.println(url);
						if(!url.startsWith("http")) {
							url = "http:"+url;
						}
						if(url.contains("subject") || url.contains("sports")) {
							continue;
						}
						if(title ==null) {
							continue;
						}
						if(title.contains("?.")) {
							title.replace("?.", "");
						}
						//进行反扒机制
						if(url!="http//www.sohu.com/a/377740132_162522") {
							//连接url这个路径
							Document document2 = Jsoup.connect(url).get();
							//读取文章到相关页面
							Elements article = document2.select("article");
							String text =null;
							for (Element element2 : article) {
								text =element2.text();
								
									text = text.replace(".txt", "");
							}
							if(title.contains("|") || title.contains(":")) {
								title =title.replace("|", "");
								title =title.replace(":", "");
							}
							
							if(text!=null) {
								
								FileUtil.writeFile("D:\\1710D2Spider\\"+title+".txt", text, "utf8");
							}else {
								continue;
							}
							
						}
						
						
					}
				}
				
			}
	
		

}

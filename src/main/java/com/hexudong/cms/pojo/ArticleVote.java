package com.hexudong.cms.pojo;

public class ArticleVote {

	/**  **/
	private Integer id;
	/** 文章Id **/
	private Integer article_id;
	/** 用户Id **/
	private Integer user_id;
	/** 选项 **/
	private String option;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	@Override
	public String toString() {
		return "ArticleVote [id=" + id + ", article_id=" + article_id + ", user_id=" + user_id + ", option=" + option
				+ "]";
	}
	public ArticleVote(Integer id, Integer article_id, Integer user_id, String option) {
		super();
		this.id = id;
		this.article_id = article_id;
		this.user_id = user_id;
		this.option = option;
	}
	public ArticleVote() {
		super();
	}

	
}

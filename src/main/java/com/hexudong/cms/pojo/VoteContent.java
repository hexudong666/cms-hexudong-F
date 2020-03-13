package com.hexudong.cms.pojo;



public class VoteContent {

	/**  **/
	private Integer id;
	/** 标题 **/
	private String title;
	/** 内容 **/
	private String content;


	public VoteContent(Integer id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}


	@Override
	public String toString() {
		return "VoteContent [id=" + id + ", title=" + title + ", content=" + content + "]";
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public VoteContent() {
		super();
	}

	
}

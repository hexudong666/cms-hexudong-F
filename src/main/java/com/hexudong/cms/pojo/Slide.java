package com.hexudong.cms.pojo;

public class Slide {

	/**  **/
	private Integer id;
	/** 标题 **/
	private String title;
	/** 图片地址 **/
	private String picture;
	/** 链接url **/
	private String url;

	public Slide(Integer id, String title, String picture, String url) {
		super();
		this.id = id;
		this.title = title;
		this.picture = picture;
		this.url = url;
	}

	@Override
	public String toString() {
		return "Slide [id=" + id + ", title=" + title + ", picture=" + picture + ", url=" + url + "]";
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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Slide() {
		super();
	}

	
}

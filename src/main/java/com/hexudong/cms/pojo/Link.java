package com.hexudong.cms.pojo;

import java.util.Date;

public class Link {

	/**  **/
	private Integer id;
	/**  **/
	private String text;
	/**  **/
	private String url;
	/**  **/
	private Date created;

	public Link(Integer id, String text, String url, Date created) {
		super();
		this.id = id;
		this.text = text;
		this.url = url;
		this.created = created;
	}

	@Override
	public String toString() {
		return "Link [id=" + id + ", text=" + text + ", url=" + url + ", created=" + created + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Link() {
		super();
	}

	
}

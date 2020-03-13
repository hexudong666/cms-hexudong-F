package com.hexudong.cms.pojo;

public class Tag {

	/**  **/
	private Integer id;
	/** 标签名称 **/
	private String tagname;

	public Tag(Integer id, String tagname) {
		super();
		this.id = id;
		this.tagname = tagname;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", tagname=" + tagname + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	public Tag() {
		super();
	}

}

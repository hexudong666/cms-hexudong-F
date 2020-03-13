package com.hexudong.cms.common;

public enum ArticleEnum {
	HTML(0,"html"),IMAGE(1,"IMAGE");
	public int id;
	public String code;
	private ArticleEnum(int id, String code) {
		this.id = id;
		this.code = code;
	}
	private ArticleEnum() {
	}
	
	
	
}

package com.hexudong.cms.pojo;

import java.io.Serializable;

public class Channel implements Serializable {

	
	    /**
	    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	    */
	    
	private static final long serialVersionUID = 2436700850925722151L;
	/**  **/
	private Integer id;
	/** 名称 **/
	private String name;
	/** 描述 **/
	private String description;
	/** 图标 **/
	private String icon;
	/** 排序 **/
	private Integer sorted;

	public Channel(Integer id, String name, String description, String icon, Integer sorted) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.icon = icon;
		this.sorted = sorted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSorted() {
		return sorted;
	}

	public void setSorted(Integer sorted) {
		this.sorted = sorted;
	}



	
}

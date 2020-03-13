package com.hexudong.cms.pojo;

import java.util.Date;

public class User {

	//扩展属性第二次验证密码
	
	private String rePassword;
	/**  **/
	private Integer id;
	/** 登录名称 **/
	private String username;
	/** 用户头像 **/
	private String headimg;
	/** 登录密码 **/
	private String password;
	/** 昵称 **/
	private String nickname;
	/** 生日 **/
	private Date birthday;
	/** 性别，1：男；2：女 **/
	private Integer gender;
	/** 0:正常,1:禁用 **/
	private Integer locked;
	/** 积分 **/
	private Integer score;
	/** 0:普通用户,1:管理员 **/
	private String role;
	/** 个人博客网址 **/
	private String url;
	/** 创建时间 **/
	private Date create_time;
	/** 修改时间 **/
	private Date update_time;
	
	
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Integer getLocked() {
		return locked;
	}
	public void setLocked(Integer locked) {
		this.locked = locked;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	public User(String rePassword, Integer id, String username, String headimg, String password, String nickname,
			Date birthday, Integer gender, Integer locked, Integer score, String role, String url, Date create_time,
			Date update_time) {
		super();
		this.rePassword = rePassword;
		this.id = id;
		this.username = username;
		this.headimg = headimg;
		this.password = password;
		this.nickname = nickname;
		this.birthday = birthday;
		this.gender = gender;
		this.locked = locked;
		this.score = score;
		this.role = role;
		this.url = url;
		this.create_time = create_time;
		this.update_time = update_time;
	}
	@Override
	public String toString() {
		return "User [rePassword=" + rePassword + ", id=" + id + ", username=" + username + ", headimg=" + headimg
				+ ", password=" + password + ", nickname=" + nickname + ", birthday=" + birthday + ", gender=" + gender
				+ ", locked=" + locked + ", score=" + score + ", role=" + role + ", url=" + url + ", create_time="
				+ create_time + ", update_time=" + update_time + "]";
	}
	public User() {
		super();
	}

	
}

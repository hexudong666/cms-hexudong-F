package com.hexudong.cms.pojo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.hexudong.cms.common.ArticleEnum;

//指定文档对象和数据库名
@Document(indexName = "cms_article",type = "article")
public class Article {

	/** 所属栏目 **/
	private String channel_name;
	/** 所属分类 **/
	private String category_name;
	/** 用户昵称 **/
	private String nickname;
	
	private String statusStr;
	
	/**
	 * 图片描述
	 */
	private String desc;
	
	private ArticleEnum articleEnum;

	private int sid;
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public ArticleEnum getArticleEnum() {
		return articleEnum;
	}
	public void setArticleEnum(ArticleEnum articleEnum) {
		this.articleEnum = articleEnum;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	/**  **/
	@Id
	private Integer id;
	/** 标题 **/
	//1.本字段是否索引,2指定的name值是否被存储3，这个值的分词方式，4关键词的分词方式，5存储类型
	@Field(index = true,store = true,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word",type = FieldType.text)
	private String title;
	/** 文章内容 **/
	//1.本字段是否索引,2指定的name值是否被存储3，这个值的分词方式，4关键词的分词方式，5存储类型
	@Field(index = true,store = true,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word",type = FieldType.text)
	private String content;
	/** 标题图片 **/
	private String picture;
	/** 所属栏目 **/
	private Integer channel_id;
	/** 所属分类 **/
	private Integer category_id;
	/** 用户Id **/
	private Integer user_id;
	/** 点击次数 **/
	private Integer hits;
	/** 是否热门 **/
	private Integer hot;
	/** 0：刚发布；2：草稿；1审核通过；-1 审核未通过;3:禁用 **/
	private Integer status;
	/** 0:正常,1:删除 **/
	private Integer deleted;
	/** 发布时间 **/
	private Date created;
	/** 更新时间 **/
	private Date updated;
	/** 评论数量 **/
	private Integer commentCnt;
	/** 投诉次数 **/
	private Integer tousuCnt;

	public String getChannel_name() {
		return channel_name;
	}
	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getStatusStr() {
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
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
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Integer getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(Integer channel_id) {
		this.channel_id = channel_id;
	}
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getHits() {
		return hits;
	}
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	public Integer getHot() {
		return hot;
	}
	public void setHot(Integer hot) {
		this.hot = hot;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public Integer getCommentCnt() {
		return commentCnt;
	}
	public void setCommentCnt(Integer commentCnt) {
		this.commentCnt = commentCnt;
	}
	public Integer getTousuCnt() {
		return tousuCnt;
	}
	public void setTousuCnt(Integer tousuCnt) {
		this.tousuCnt = tousuCnt;
	}
	@Override
	public String toString() {
		return "Article [channel_name=" + channel_name + ", category_name=" + category_name + ", nickname=" + nickname
				+ ", statusStr=" + statusStr + ", desc=" + desc + ", articleEnum=" + articleEnum + ", sid=" + sid
				+ ", id=" + id + ", title=" + title + ", content=" + content + ", picture=" + picture + ", channel_id="
				+ channel_id + ", category_id=" + category_id + ", user_id=" + user_id + ", hits=" + hits + ", hot="
				+ hot + ", status=" + status + ", deleted=" + deleted + ", created=" + created + ", updated=" + updated
				+ ", commentCnt=" + commentCnt + ", tousuCnt=" + tousuCnt + "]";
	}
	public Article(String channel_name, String category_name, String nickname, String statusStr, String desc,
			ArticleEnum articleEnum, int sid, Integer id, String title, String content, String picture,
			Integer channel_id, Integer category_id, Integer user_id, Integer hits, Integer hot, Integer status,
			Integer deleted, Date created, Date updated, Integer commentCnt, Integer tousuCnt) {
		super();
		this.channel_name = channel_name;
		this.category_name = category_name;
		this.nickname = nickname;
		this.statusStr = statusStr;
		this.desc = desc;
		this.articleEnum = articleEnum;
		this.sid = sid;
		this.id = id;
		this.title = title;
		this.content = content;
		this.picture = picture;
		this.channel_id = channel_id;
		this.category_id = category_id;
		this.user_id = user_id;
		this.hits = hits;
		this.hot = hot;
		this.status = status;
		this.deleted = deleted;
		this.created = created;
		this.updated = updated;
		this.commentCnt = commentCnt;
		this.tousuCnt = tousuCnt;
	}
	public Article() {
		super();
	}


	
}

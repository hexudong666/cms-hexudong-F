package com.hexudong.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.hexudong.cms.pojo.Comment;

public interface CommentMapper extends BaseDao<Comment> {

	@Insert("INSERT INTO cms_comment(articleId,userId,content,created)VALUES(#{articleId},#{userId},#{content},#{created})")
	void addComment(Comment comment);
	@Select("SELECT * FROM cms_comment WHERE articleId=#{id}")
	List<Comment> selectComment(Integer id);


}

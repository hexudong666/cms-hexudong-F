package com.hexudong.cms.service;

import com.github.pagehelper.PageInfo;
import com.hexudong.cms.pojo.Comment;

public interface CommentService {

	void addComment(Comment comment);

	PageInfo<Comment> selectService(Integer page, Integer pageSize, Integer id);

}

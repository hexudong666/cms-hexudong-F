package com.hexudong.cms.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hexudong.cms.dao.CommentMapper;
import com.hexudong.cms.dao.UserMapper;
import com.hexudong.cms.pojo.Comment;
import com.hexudong.cms.pojo.User;
import com.hexudong.cms.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Resource
	private CommentMapper commentMapper;
	@Autowired
	private UserMapper userMapper;
	@Override
	public void addComment(Comment comment) {
		commentMapper.addComment(comment);
		
	}
	@Override
	public PageInfo<Comment> selectService(Integer page, Integer pageSize, Integer id) {
		PageHelper.startPage(page, pageSize);
		List<Comment> list =  commentMapper.selectComment(id);
		list.forEach(c->{
			User user = userMapper.selectById(c.getUserId());
			c.setCname(user.getUsername());
		});
		return new PageInfo<Comment>(list);
	}
}

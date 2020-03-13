package com.hexudong.cms.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hexudong.cms.common.CmsMd5Util;
import com.hexudong.cms.dao.UserMapper;
import com.hexudong.cms.pojo.User;
import com.hexudong.cms.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private 	UserMapper userMapper;

	@Override
	public boolean register(User user) {
		/** 设置密码 **/
		user.setPassword(CmsMd5Util.md5(user.getPassword()));
		user.setLocked(0);
		user.setScore(0);
		user.setRole("0");
		user.setCreate_time(new Date());
		user.setUpdate_time(new Date());
		return userMapper.insert(user)>0;
	}

	@Override
	public User getByUsername(String userName) {
		return userMapper.selectByUsername(userName);
	}

	@Override
	public boolean locked(String userName) {
		User userInfo = userMapper.selectByUsername(userName);
		return userInfo.getLocked()==1;
	}

	@Override
	public boolean set(User user) {
		User userInfo = userMapper.selectById(user.getId());
		userInfo.setHeadimg(user.getHeadimg());
		userInfo.setNickname(user.getNickname());
		return userMapper.update(userInfo)>0;
	}

	@Override
	public User getById(Integer id) {
		return userMapper.selectById(id);
	}

	@Override
	public PageInfo<User> getPageInfo(User user, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<User> userList = userMapper.select(user);
		return new PageInfo<>(userList);
	}

	@Override
	public boolean updateLocked(Integer id) {
		User user = userMapper.selectById(id);
		if(user.getLocked()==0) {
			user.setLocked(1);
		}else {
			user.setLocked(0);
		}
		return userMapper.update(user)>0;
	}
	
}

package com.hexudong.cms.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexudong.cms.dao.LinkMapper;
import com.hexudong.cms.pojo.Link;
import com.hexudong.cms.service.LinkService;
@Service
public class LinkServiceImpl implements LinkService {

	@Autowired
	private LinkMapper linkMapper;
	@Override
	public List<Link> select() {
		// TODO Auto-generated method stub
		return linkMapper.select();
	}

}

package com.hexudong.cms.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexudong.cms.dao.SlideMapper;
import com.hexudong.cms.pojo.Slide;
import com.hexudong.cms.service.SlideService;

@Service
public class SlideServiceImpl implements SlideService{
	@Autowired
	private SlideMapper slideMapper;
	@Override
	public List<Slide> getAll() {
		return slideMapper.select(null);
	}

}

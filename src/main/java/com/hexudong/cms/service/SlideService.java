package com.hexudong.cms.service;

import java.util.List;

import com.hexudong.cms.pojo.Slide;

public interface SlideService {
	/**
	 * @Title: getAll   
	 * @Description: 查询所有轮播图   
	 * @param: @return      
	 * @return: List<Slide>      
	 * @throws
	 */
	List<Slide> getAll();
}

package com.hexudong.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hexudong.cms.exception.CMSRuntimeException;
import com.hexudong.cms.pojo.Collect;

public interface CollectService {

	int add(Collect collect) throws CMSRuntimeException;

	PageInfo<Collect> selectCellects(Integer id, Integer page, Integer pageSize);

	void delete(Integer id);

	List<Collect> select();

	Collect selectText(String text, Integer integer);

}

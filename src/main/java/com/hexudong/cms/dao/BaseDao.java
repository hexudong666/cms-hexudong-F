package com.hexudong.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaseDao<T> {

	//列表查询
		List<T> select(T t);
		//单条数据查询
		T selectById(Integer id);
		//单条修改
		int update(T t);
		//删除
		int delete(@Param("ids")String ids);
		//添加
		int insert(T t);
}

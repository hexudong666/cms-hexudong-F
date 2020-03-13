package com.hexudong.cms.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hexudong.cms.pojo.Collect;

public interface CollectMapper extends BaseDao<Collect> {

	int add(Collect collect);



	void delete(Integer id);

	List<Collect> select();

	Collect selectText(@Param("text")String text,@Param("integer") Integer integer);



	List<Collect> selectCellects(Integer id);


}

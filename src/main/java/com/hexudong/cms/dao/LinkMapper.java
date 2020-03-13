package com.hexudong.cms.dao;

import java.util.List;

import com.hexudong.cms.pojo.Link;

public interface LinkMapper extends BaseDao<Link> {

	List<Link> select();


}

package com.hexudong.cms.dao;


import org.apache.ibatis.annotations.Select;

import com.hexudong.cms.pojo.User;

public interface UserMapper extends BaseDao<User>{

	/**
	 * @Title: selectByUsername   
	 * @Description: 根据用户名查找用户   
	 * @param: @param userName
	 * @param: @return      
	 * @return: User      
	 * @throws
	 */
	@Select("select * from cms_user where username=#{userName}")
	User selectByUsername(String userName);

}

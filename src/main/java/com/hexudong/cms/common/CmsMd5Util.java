package com.hexudong.cms.common;

import com.hexudong.utils.Md5Util;

public class CmsMd5Util {
	/**
	 * @Title: md5   
	 * @Description: cms密码加密   
	 * @param: @param str
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String md5(String str) {
		String string2md5 = Md5Util.string2MD5(str+"cmsPassword");
		return string2md5;
	}
}

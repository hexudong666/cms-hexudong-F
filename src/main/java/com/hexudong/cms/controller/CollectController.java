package com.hexudong.cms.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hexudong.cms.common.CmsConst;
import com.hexudong.cms.exception.CMSRuntimeException;
import com.hexudong.cms.pojo.Collect;
import com.hexudong.cms.pojo.User;
import com.hexudong.cms.service.CollectService;
import com.hexudong.utils.StringUtil;

/**
 * 收藏文章
 * @author ASUS
 *
 */
@Controller
@RequestMapping("collect")
public class CollectController {

	@Resource
	private CollectService collectService;
	
	@RequestMapping("add")
	@ResponseBody
	public int add(Collect collect,HttpSession session) {
		boolean httpUrl = StringUtil.isHttpUrl(collect.getUrl());
		
		//获取本地时间
		int s=0;
		Date date = new Date();
		String localeString = date.toLocaleString();
		User attribute = (User) session.getAttribute(CmsConst.UserSessionKey);
		//查询当前登录用户收藏的名字是否有值
		Collect coll = collectService.selectText(collect.getText(),attribute.getId());
		if(coll==null) {
			collect.setCreated(localeString);
			try {
				s = collectService.add(collect);
				return s;
			} catch (CMSRuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(collect);
			return s;
		}else {
			return s;
		}
		
	}
	@RequestMapping("delete")
	public String delete(Integer id,HttpSession session) {
		System.out.println(id);
		//判断是否是登录用户
		User attribute = (User) session.getAttribute(CmsConst.UserSessionKey);
		if(attribute==null) {
			return "redirect:user/login";
			
		}else {
			
			collectService.delete(id);
			return "redirect:/user/center";
		}
		
	}
	
}

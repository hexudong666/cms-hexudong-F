package com.hexudong.cms.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hexudong.cms.common.CmsConst;
import com.hexudong.cms.common.JsonResult;
import com.hexudong.cms.pojo.Comment;
import com.hexudong.cms.pojo.User;
import com.hexudong.cms.service.CommentService;


@Controller
@RequestMapping("/comment/")
public class CommentController {

	@Resource
	private CommentService commentService;
	/**
	 * @param comment
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public JsonResult show(Comment comment,HttpSession session) {
	
		//后端验证是否登陆
		User user = (User) session.getAttribute(CmsConst.UserSessionKey);
		if(user==null) {
			return JsonResult.fail(10000,"用户未登录");
		}
		else {
			Date date = new Date();
			String localeString = date.toLocaleString();
			comment.setCreated(localeString);
			System.out.println(comment);
			commentService.addComment(comment);
			//返回成功集
			return JsonResult.sucess();
		}
		
		
	}
}

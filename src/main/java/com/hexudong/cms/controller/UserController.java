package com.hexudong.cms.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hexudong.cms.common.CmsConst;
import com.hexudong.cms.common.CmsMd5Util;
import com.hexudong.cms.common.JsonResult;
import com.hexudong.cms.pojo.User;
import com.hexudong.cms.service.UserService;
import com.hexudong.utils.StringUtil;

@Controller
@RequestMapping("/user/")
public class UserController {
	@Autowired
	private UserService userService;
	/**
	 * @Title: userCenter   
	 * @Description: 用户中心   
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/center")
	public String userCenter() {
		return "user/center";
	}
	/**
	 * @Title: login   
	 * @Description: 跳转到登录页面   
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@GetMapping("/login")
	public String tologin() {
		return "user/login";
	}
	
	@PostMapping("/login")
	public @ResponseBody JsonResult login(User user,HttpSession session) {
		/** 用户名和密码不能为空 **/
		if(StringUtil.isNull(user.getUsername()) || StringUtil.isNull(user.getPassword()) ) {
			return JsonResult.fail();
		}
		/** 判断用户是否存在 **/
		User userInfo = userService.getByUsername(user.getUsername());
		if(userInfo==null) {
			return JsonResult.fail(10000,"用户名或密码不正确");
		}
		/** 判断用户是否被禁用 **/
		if(userService.locked(user.getUsername())) {
			return JsonResult.fail(10000,"该用户已被禁用，请联系管理员");
		}
		/** 判断密码是否正确 **/
		String password = user.getPassword();
		String md5Password = CmsMd5Util.md5(password);
		if(!md5Password.equals(userInfo.getPassword())) {
			return JsonResult.fail(10000,"用户名或密码不正确");
		}
		/** 设置session **/
		session.setAttribute(CmsConst.UserSessionKey, userInfo);
		return JsonResult.sucess();
	}
	/**
	 * @Title: signOut   
	 * @Description: 退出登录  
	 * @param: @param response
	 * @param: @param session
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@GetMapping("/signOut")
	public String signOut(HttpServletResponse response,HttpSession session) {
		session.setAttribute(CmsConst.UserSessionKey, null);
		return "redirect:/";
	}
	/**
	 * @Title: register   
	 * @Description: 跳转到注册页面   
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String toRegister() {
		return "user/register";
	}
	/**
	 * @Title: register   
	 * @Description: 用户注册   
	 * @param: @param user
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	@PostMapping("/register")
	public @ResponseBody JsonResult register(User user) {
		/** 用户名和密码不能为空 **/
		if(StringUtil.isNull(user.getUsername()) || StringUtil.isNull(user.getPassword()) 
				|| StringUtil.isNull(user.getRePassword())) {
			return JsonResult.fail();
		}
		/** 判断两次输入的密码是否一致 **/
		if(!user.getPassword().equals(user.getRePassword())) {
			return JsonResult.fail(10000,"两次输入的密码不一致");
		}
		/** 判断用户是否存在 **/
		User userInfo = userService.getByUsername(user.getUsername());
		if(userInfo!=null) {
			return JsonResult.fail(10000,"用户已经存在");
		}
		/** 用户注册 **/
		boolean result = userService.register(user);
		if(result) {
			return JsonResult.sucess();
		}
		return JsonResult.fail();
	}
	/**
	 * @Title: article   
	 * @Description: 文章管理  
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/article")
	public String article() {
		return "user/article";
	}
	/**
	 * 
	    * @Title: set
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param @param session
	    * @param @param model
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@GetMapping("/set")
	public String set(HttpSession session,Model model) {
		User userInfo = (User)session.getAttribute(CmsConst.UserSessionKey);
		User user = userService.getByUsername(userInfo.getUsername());
		model.addAttribute("user", user);
		return "user/set";
	}
	
	@PostMapping("/set")
	public @ResponseBody JsonResult setUser(HttpSession session,User user) {
		User userInfo = (User)session.getAttribute(CmsConst.UserSessionKey);
		user.setId(userInfo.getId());
		userService.set(user);
		return JsonResult.sucess();
	}
	
}

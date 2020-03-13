package com.hexudong.cms.controller.admin;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.hexudong.cms.common.CmsConst;
import com.hexudong.cms.common.CmsMd5Util;
import com.hexudong.cms.common.JsonResult;
import com.hexudong.cms.pojo.Article;
import com.hexudong.cms.pojo.User;
import com.hexudong.cms.service.ArticleService;
import com.hexudong.cms.service.UserService;
import com.hexudong.utils.StringUtil;


@Controller
@RequestMapping("/admin/")
public class AdminController {
	@Autowired
	private UserService userService;
	@Autowired
	private ArticleService articleService;
	
	/**
	 * @Title: tologin   
	 * @Description: 登录页面   
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@GetMapping("/")
	public String tologin() {
		return "admin/login";
	}
	/**
	 * @Title: login   
	 * @Description: 管理员登录   
	 * @param: @param user
	 * @param: @param session
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
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
		/** 是否为管理员 **/
		if(!"1".equals(userInfo.getRole())) {
			return JsonResult.fail(10000,"只有管理员才能登录");
		}
		/** 判断密码是否正确 **/
		String password = user.getPassword();
		String md5Password = CmsMd5Util.md5(password);
		if(!md5Password.equals(userInfo.getPassword())) {
			return JsonResult.fail(10000,"用户名或密码不正确");
		}
		/** 设置session **/
		session.setAttribute(CmsConst.UserAdminSessionKey, userInfo);
		return JsonResult.sucess();
	}
	
	@GetMapping("/signOut")
	public String signOut(HttpServletResponse response,HttpSession session) {
		session.setAttribute(CmsConst.UserAdminSessionKey, null);
		return "redirect:/admin/";
	}
	
	@RequestMapping("/center")
	public String userCenter() {
		return "admin/center";
	}
	
	@RequestMapping("/user")
	public String user(User user,Model model,
			@RequestParam(value="pageNum",defaultValue="1") Integer pageNum,@RequestParam(value="pageSize",defaultValue="3") Integer pageSize) {
		PageInfo<User> pageInfo = userService.getPageInfo(user,pageNum,pageSize);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/user";
	}
	
	@RequestMapping("/updateLocked")
	public @ResponseBody JsonResult updateLocked(User user) {
		boolean result = userService.updateLocked(user.getId());
		return JsonResult.sucess();
	}
	
	@RequestMapping("/articles")
	public String articles(Article article,Model model,HttpSession session,
			@RequestParam(value="pageNum",defaultValue="1") Integer pageNum,
			@RequestParam(value="pageSize",defaultValue="2") Integer pageSize) {
		article.setStatusStr("0,1,-1,3");
		PageInfo<Article> pageInfo = articleService.getPageInfo(article, pageNum, pageSize);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/articles";
	}
	/**
	 * 
	    * @Title: check
	    * @Description: 审核文章
	    * @param @param article
	    * @param @return    参数
	    * @return JsonResult    返回类型
	    * @throws
	 */
	@RequestMapping("/check")
	public @ResponseBody JsonResult check(Article article) {
		System.out.println("审核文章");
		articleService.check(article);
		return JsonResult.sucess();
	}

}

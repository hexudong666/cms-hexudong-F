package com.hexudong.cms.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class UserAuthInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object userInfo = request.getSession().getAttribute(CmsConst.UserSessionKey);
		if(userInfo==null) {
			response.sendRedirect("/user/login");
			return false;
		}
		return true;
	}
	
}

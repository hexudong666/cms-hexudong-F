package com.hexudong.cms.common;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class UserAdminAuthInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object userInfo = request.getSession().getAttribute(CmsConst.UserAdminSessionKey);
		if(userInfo==null) {
			response.sendRedirect("/admin/");
			return false;
		}
		return true;
	}
	
}

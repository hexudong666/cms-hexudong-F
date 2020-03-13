<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="header">
	<ul class="nav">
		<li class="nav-item" style="margin-left: 12px;">
			<a class="navbar-brand" href="#"> 
				<c:if test="${userAdminInfo!=null }">
					<img src="${userInfo.headimg }"	width="30" height="30" alt="">
				</c:if>
				<c:if test="${userAdminInfo==null }">
					<img src="https://v4.bootcss.com/docs/4.3/assets/brand/bootstrap-solid.svg"	width="30" height="30" alt="">
				</c:if>
			</a>
		</li>
		<li class="nav-item"><a class="nav-link active" href="/" target="_blank">网站首页</a>	</li>
		
		<c:if test="${userAdminInfo==null }">
		<li class="nav-item"><a class="nav-link" href="/admin/">登录</a></li>
		</c:if>
		<c:if test="${userAdminInfo!=null }">
		<li class="nav-item"><a class="nav-link" href="/admin/是">退出</a></li>
		</c:if>
	</ul>
</div>
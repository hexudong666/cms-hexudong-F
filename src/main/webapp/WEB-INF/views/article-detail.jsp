<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${article.title }</title>
<link href="/public/css/bootstrap.min.css" rel="stylesheet">
<link href="/public/css/index.css" rel="stylesheet">
</head>
<body>

	<div class="header">
		<jsp:include page="./common/user/head-top.jsp"></jsp:include>
	</div>
	<div class="container-fluid" style="margin-top: 6px;">
		<div class="row offset-1">
			<div class="col-6">
				<h1>${article.title }</h1>
				<!-- 隐藏id值 -->
				<form id="fsd">
					<input type="hidden" value="${us.id}" name="user_id">
					<input type="hidden" value="${article.title}" name="text">
				</form>
				<h3 style="color: #777;"> 发布时间：<fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd日"/></h3>
					<div class="article-content">
						${article.content }
							<input type="button" value="收藏" onclick="sc()" class="btn btn-primary" id="fdd" >
						
					</div>
				<div class="container-fluid" style="margin-top: 20px">
					<form id="fid">
					<c:if test="${userInfo==null}">
						<span  ><a href="/user/login" style="color: red;">请登录后，发表评论</a></span>
					</c:if>
					<c:if test="${userInfo!=null}">
					<input type="hidden" name="articleId" value="${article.id}" >
					<input type="hidden" name="userId" value="${us.id}" >
						 <textarea class="form-control" id="content" name="content" rows="1" placeholder="请输入评论" style="width: 300px;height: 60px" ></textarea>
					 <button type="button" class="btn btn-primary" onclick="submitComment()">发布</button>
					</c:if>
					</form>
					<div>
					<c:forEach items="${info.list}" var="stu" >
					<ul class="list-group list-group-flush">
					<li class="list-group-item">${stu.cname}</t> ${stu.created} <br> ${stu.content}</li>
					</ul>
					</c:forEach>
					
				</div>
				
			</div>
			<jsp:include page="pages.jsp"></jsp:include>
		</div>
		
			<div class="col-3">
				<div class="right">
					<div>相关文章</div>
					<ul class="list-unstyled">
						<c:forEach items="${newArticleList}" var="item">
						<li class="media">
							<a href="/article/detail/${item.id}.html"><img src="${item.picture }"	style="height: 72px; width: 72px;" class="mr-3" alt="..."></a>
							<div class="media-body">
								<h5 class="mt-0 mb-1"><a href="/article/detail/${item.id }.html">${item.title }</a></h5>
							</div></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
		<li> 友情链接:</li>
		
		<c:forEach items="${LinkList}" var="s" >
			<li> <a href="${s.url}" style="margin-left: 60px;" >${s.text}</a> </li>
		</c:forEach>
			
		</ol>
	</nav>
	<script src="/public/js/jquery.min.1.12.4.js"></script>
	<script src="/public/js/bootstrap.min.js"></script>

</body>
	<script type="text/javascript">
		function submitComment(){
			/* 把form表单提交 */
			var forData = $("#fid").serialize();
			$.post('/comment/add',forData,function(res){
				if(res.result){
					console.log("评论成功");
					var href = location.href;
					location.href=href.substring(0,href.indexOf('?'));
				}else if(res.errorCode==10000){
					alert(res.message);
					location.href="/user/login"
				}else{
					console.log("评论失败");
				}
			})
		}
		function goPage(page){
			var id = '${id}';
			location.href="/article/detail/"+id+".html?page="+page;
		}
		//进行收藏文章
		function sc(){
			//如果session里面没有值就是没有登录，进行登录页面
			if(${userInfo==null}){
				alert("您没有登录，即将进行登录页面")
				location.href="user/login";
			}
			else{
				//获取本路径的地址
				var url = window.location.href;
				var fsd =$("#fsd").serialize(); 
				//type类型，路径，传值，成功回调函数
				$.post('/collect/add?url='+url,fsd,function(msg){
					if(msg>0){
						alert("收藏成功");
						$("#fdd").remove();
					}else{
						alert("您已经收藏了不能重复收藏");
					}
				})
			}
			
		}
	</script>
</html>
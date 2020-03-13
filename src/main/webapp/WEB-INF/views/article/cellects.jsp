<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>??</title>
<link href="/public/css/bootstrap.min.css" rel="stylesheet">
<link href="/public/css/index.css" rel="stylesheet">

</head>
<body>
		<div class="container-fluid" style="margin-top: 20px">
			<h4>我的收藏夹</h4>
			<c:forEach items="${info.list}" var="stu"  >
				<ul class="list-group list-group-flush">
					<li class="list-group-item"> <a href="/article/detail/${stu.cid}.html" >${stu.text}</a> <br> 时间:${stu.created}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="/collect/delete?id=${stu.id}" >删除</a> </li>
					<li class="list-group-item"></li>
					
					</ul>
			</c:forEach>
			<jsp:include page="../pages.jsp"></jsp:include>
		</div>
	<script src="/public/js/jquery.min.1.12.4.js"></script>
	<script src="/public/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function goPage(page) {
			location.href="/article/cellects?page="+page;
		}
	</script>
</body>
</html>
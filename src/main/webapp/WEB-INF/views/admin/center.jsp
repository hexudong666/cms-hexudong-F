<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CMS后台管理</title>
<link href="/public/css/bootstrap.min.css" rel="stylesheet">
<link href="/public/css/index.css" rel="stylesheet">

<link rel="stylesheet" href="/public/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="/public/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="/public/kindeditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="/public/kindeditor/lang/zh-CN.js"></script>
<script charset="utf-8" src="/public/kindeditor/plugins/code/prettify.js"></script>
<script type="text/javascript">
KindEditor.ready(function(K) {});
</script>
</head>
<body>
	<!-- 头部导航 -->
	<jsp:include page="../common/admin/head-top.jsp"></jsp:include>
	<div class="container-fluid" style="margin-top: 6px;">
		<div class="row offset-1" style="margin-top: 10px;">
			<div class="col-2">
				<div class="nav flex-column nav-pills" aria-orientation="vertical">
					<a class="nav-link active" href="javascript:;" url="/admin/user">用户管理</a>
					<a class="nav-link" href="javascript:;" url="/admin/articles">文章管理</a> 
				</div>
			</div>
			<div class="col-8" id="main">
				<!-- 通过ajax异步加载 -->
				
			</div>
		</div>
	</div>
	<script src="/public/js/jquery.min.1.12.4.js"></script>
	<script src="/public/js/bootstrap.min.js"></script>
	<script src="/public/js/cms.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台登录</title>
<link href="/public/css/bootstrap.min.css" rel="stylesheet">
<link href="/public/css/index.css" rel="stylesheet">
</head>
<body>
	<!-- 头部导航 -->
	<jsp:include page="../common/user/head-top.jsp"></jsp:include>
	
	<div class="container-fluid" style="margin-top: 6px;">
		<div class="row offset-4" style="margin-top: 180px;">
			<div class="col-6">
				<h1>欢迎使用CMS后台管理系统</h1>
				<div class="alert alert-success" role="alert" style="display: none"></div>
				<form id="loginForm">
					<div class="form-group">
						<input type="email" class="form-control" id="username" name="username"	placeholder="请输入用户名" >
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="请输入密码"  id="password" name="password">
					</div>
					<div class="form-group form-check">
						<input type="checkbox" class="form-check-input" id="exampleCheck1">
						<label class="form-check-label" for="exampleCheck1">记住登录状态</label>
					</div>
					<div class="row">
						<div class="col-4">
							<button type="button" class="btn btn-primary" onclick="login();">登录</button>
						</div>
						<div class="col-8">
							<small id="emailHelp" class="form-text text-muted">没有帐号，去<a
								href="/user/register">注册</a></small>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="/public/js/jquery.min.1.12.4.js"></script>
	<script src="/public/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function login(){
			var username = $("#username").val();
			var password = $("#password").val();
			console.log(username)
			if(username==null || username=="" || password==null || password==""){
				$(".alert").html("用户名和密码不能为空");
				$(".alert").show();
				return;
			}
			
			$(".alert").hide();
			$.post("/admin/login",$("#loginForm").serialize(),function(res){
				if(res.result){
					location.href = "/admin/center";
				}else{
					$(".alert").html(res.message);
					$(".alert").show();
				}
			})
		}
	
	</script>
</body>
</html>
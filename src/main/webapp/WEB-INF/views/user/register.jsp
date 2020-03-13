<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<link href="/public/css/bootstrap.min.css" rel="stylesheet">
<link href="/public/css/index.css" rel="stylesheet">
</head>
<body>
	<!-- 头部导航 -->
	<jsp:include page="../common/user/head-top.jsp"></jsp:include>
	
	<div class="container-fluid" style="margin-top: 6px;">
		<div class="row offset-4" style="margin-top: 180px;">
			<div class="col-4">
				<h1>欢迎回来</h1>
				<div class="alert alert-success" role="alert" style="display: none"></div>
				<form id="registerForm">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="请输入用户名" id="username" name="username">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="请输入密码" id="password" name="password">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="请确认密码" id="rePassword" name="rePassword">
					</div>
					<div class="form-group form-check">
						<input type="checkbox" class="form-check-input" id="exampleCheck1">
					</div>
					<div class="row">
						<div class="col-4">
							<button type="button" class="btn btn-primary" onclick="register();">注册</button>
						</div>
						<div class="col-8">
							<small id="emailHelp" class="form-text text-muted">已有帐号，去<a href="/user/login">登录</a></small>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="/public/js/jquery.min.1.12.4.js"></script>
	<script src="/public/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function register(){
			var username = $("#username").val();
			var password = $("#password").val();
			var rePassword = $("#rePassword").val();
			console.log(username)
			if(username==null || username=="" || password==null || password=="" || rePassword==null || rePassword==""){
				$(".alert").html("用户名和密码不能为空");
				$(".alert").show();
				return;
			}
			
			if(password!=rePassword){
				$(".alert").html("两次输入的密码不一致");
				$(".alert").show();
				return;
			}
			$(".alert").hide();
			$.post("/user/register",$("#registerForm").serialize(),function(res){
				if(res.result){
					location.href = "/user/login";
				}else{
					$(".alert").html(res.message);
					$(".alert").show();
				}
			})
		}
	
	</script>
</body>
</html>
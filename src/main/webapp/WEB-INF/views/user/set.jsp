<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form id="saveForm">
	<div class="form-group row">
		<label for="inputEmail3" class="col-sm-2 col-form-label">我的昵称</label>
		<div class="col-sm-5">
			<input type="email" class="form-control" id="nickname" name="nickname" value="${user.nickname }">
		</div>
	</div>
	<div class="form-group row">
		<label for="inputPassword3" class="col-sm-2 col-form-label">我的头像</label>
		<div class="col-sm-5">
			<jsp:include page="../common/file.jsp">
				<jsp:param name="fieldName" value="headimg"/>
				<jsp:param name="fieldValue" value="${user.headimg }"/>
			</jsp:include>
		</div>
	</div>
	<button type="button" class="btn btn-primary mb-2" onclick="save();">保存</button>
	<div class="alert alert-success" role="alert" style="display: none"></div>
</form>

<script type="text/javascript">
	
	function save(){
		var nickname = $("#nickname").val();
		var headimg = $("#headimg").val();
		if(nickname==null || nickname==""){
			$(".alert").html("昵称不能为空");
			$(".alert").show();
			return;
		}
		if(headimg==null || headimg==""){
			$(".alert").html("头像不能为空");
			$(".alert").show();
			return;
		}
		$(".alert").hide();
		$.post("/user/set",$("#saveForm").serialize(),function(res){
			if(res.result){
				$(".alert").html("设置成功");
				$(".alert").show();
			}
		})
		
	}
</script>
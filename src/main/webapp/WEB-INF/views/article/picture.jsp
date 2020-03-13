<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>??</title>
<link href="/public/css/bootstrap.min.css" rel="stylesheet">
<link href="/public/css/index.css" rel="stylesheet">
</head>
<!-- 发布图片 -->
<body>
<form id="fid">
	<!-- 图片描述 -->
	图片描述文本:<input type="text" name="desc" >
	<!-- 上传图片 -->
	<div class="form-group row">
		<label for="inputPassword3" class="col-sm-2 col-form-label">文章图片</label>
		<div class="col-sm-5" >
			<jsp:include page="../common/file.jsp">
				<jsp:param name="fieldName" value="picture"/>
				<jsp:param name="fieldValue" value="${article.picture }"/>
			</jsp:include>
		</div>
	</div>
	<input type="button" class="btn btn-primary mb-2" onclick="save()" value="保存" >
	</form>
	<script src="/public/js/jquery.min.1.12.4.js"></script>
	<script src="/public/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	function save() {
		var formData = new FormData($("#fid")[0]);
		$.ajax({
			url:"/article/sspicture",
			type:"post",
			data:formData,
			processData:false,
			contentType:false,
			success:function(sbj){
				if(sbj>0){
					alert("上传成功");
				}
			}
		})
	}
		
	</script>
</body>
</html>
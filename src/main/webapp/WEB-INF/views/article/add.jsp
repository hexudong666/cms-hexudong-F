<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
	var editor1 = null;
	KindEditor.ready(function(K) {
		editor1 = K.create('textarea[name="content1"]', {
			cssPath : '/public/kindeditor/plugins/code/prettify.css',
			uploadJson : '/file/uploadImg',
			fileManagerJson : '/public/kindeditor/jsp/file_manager_json.jsp',
			allowFileManager : true,
			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
			}
		});
		prettyPrint();
	});
</script>
<form id="saveForm">
	<input type="hidden" id="id" name="id" value="${article.id }">
	<div class="form-group row">
		<label for="inputEmail3" class="col-sm-2 col-form-label">文章标题</label>
		<div class="col-sm-5">
			<input type="email" class="form-control" id="title" name="title" value="${article.title }">
		</div>
	</div>
	<div class="form-group row">
		<label for="inputPassword3" class="col-sm-2 col-form-label">文章图片</label>
		<div class="col-sm-5">
			<jsp:include page="../common/file.jsp">
				<jsp:param name="fieldName" value="picture"/>
				<jsp:param name="fieldValue" value="${article.picture }"/>
			</jsp:include>
		</div>
	</div>
	<div class="form-group row">
		<label for="inputPassword3" class="col-sm-2 col-form-label">所属频道</label>
		<div class="col-sm-5">
			<select class="form-control" id="channel_id" name="channel_id" onchange="changeCate();">
				<option value="">请选择频道</option>
				<c:forEach items="${channelList }" var="item">
					<c:if test="${item.id==article.channel_id }">
						<option value="${item.id }" selected="selected">${item.name }</option>
					</c:if>
					<c:if test="${item.id!=article.channel_id }">
						<option value="${item.id }">${item.name }</option>
					</c:if>
				</c:forEach>
			</select>
		</div>
	</div>

	<div class="form-group row">
		<label for="inputPassword3" class="col-sm-2 col-form-label">所属分类</label>
		<div class="col-sm-5">
			<select class="form-control" id="category_id" name="category_id">
				<option value="">请选择分类</option>
				<c:forEach items="${cateList }" var="item">
					<c:if test="${item.id==article.category_id }">
						<option value="${item.id }" selected="selected">${item.name }</option>
					</c:if>
					<c:if test="${item.id!=article.channel_id }">
						<option value="${item.id }">${item.name }</option>
					</c:if>
				</c:forEach>
			</select>
		</div>
	</div>
	<div class="form-group row">
		<div class="col-sm-2">文章内容</div>
		<div class="col-sm-10">
			<div>
				<textarea name="content1" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;">${article.content }</textarea>
			</div>
		</div>
	</div>
	<button type="button" class="btn btn-primary mb-2" onclick="save(2);">保存</button>
	<button type="button" class="btn btn-primary mb-2" onclick="save(0);">保存并提交审核</button>
	<div class="alert alert-success" role="alert" style="display: none"></div>
</form>

<script type="text/javascript">
	function changeCate(){
		var channelId = $("#channel_id").val();
		$.get("/article/getCateListByChannelId",{channelId:channelId},function(res){
			if(res.result){
				$("#category_id").html('<option value="">请选择分类</option>');
				res.data.forEach(function(value,index){
					$("#category_id").append('<option value="'+value.id+'">'+value.name+'</option>');
				})
			}
			//
		})
	}
	
	function save(status){
		var title = $("#title").val();
		var picture = $("#picture").val();
		var channel_id = $("#channel_id").val();
		var category_id = $("#category_id").val();
		if(title==null || title==""){
			$(".alert").html("标题不能为空");
			$(".alert").show();
			return;
		}
		if(picture==null || picture==""){
			$(".alert").html("请选择图片");
			$(".alert").show();
			return;
		}
		if(channel_id==null || channel_id==""){
			$(".alert").html("请选择频道");
			$(".alert").show();
			return;
		}
		if(category_id==null || category_id==""){
			$(".alert").html("请选择分类");
			$(".alert").show();
			return;
		}
		var content = editor1.html();
		if(content==null || content==""){
			$(".alert").html("内容不能为空");
			$(".alert").show();
			return;
		}
		$(".alert").hide();
		/** 提交数据 **/
		var formData = new FormData($("#saveForm")[0]);
		formData.append("content",content);
		formData.append("status",status);
		$.ajax({
			url:"/article/save",
			type:"post",
			data:formData,
			processData:false,
			contentType:false,
			success:function(res){
				console.log(res);
				selectMenu('/article/articles');
			}
		});
	}
</script>
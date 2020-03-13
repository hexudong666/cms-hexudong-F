<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String fieldName = request.getParameter("fieldName");
	String fieldValue =  request.getParameter("fieldValue");
	String fieldValueStr = fieldValue==null || "".equals(fieldValue)?"/public/img/upload.jpg":fieldValue;
%>
<div class="input-prepend">
	<div class="imgDive">
		<img src="<%=fieldValueStr %>" class="img-rounded" width="80px;" id="viewImg">
		<div style="display: none;" id="imageuploadDiv" name="imageuploadDiv">
			<input type="file" id="fileName" name="fileName"><br> 
			<input type="hidden" id="<%=fieldName %>" name="<%=fieldName %>" value="<%=fieldValue%>">
		</div>
	</div>
</div>
<script type="text/javascript">

$(".imgDive").mouseover(function () {
	$(this).find("#imageuploadDiv").css("display","block");
});
$(".imgDive").mouseleave(function () {
    $(this).find("#imageuploadDiv").css("display","none");
});

$("#fileName").on("change",function(){
		console.log(this.files[0]);
		var formData = new FormData();
		formData.append("file",this.files[0]);
		$.ajax({
			url:"/file/upload",
			type:"post",
			data:formData,
			processData:false,
			contentType:false,
			success: function(res){
				$("#viewImg").attr("src",res.url);
				$("#<%=fieldName %>").val(res.url);
			}
		});
	})
</script>
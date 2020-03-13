<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<form class="form-inline" id="queryForm">
	<div class="form-group mx-sm-3 mb-2">
		<input type="text" class="form-control" id="title" name="title" value="${article.title }" placeholder="请输入文章标题">
	</div>
	<div class="form-group mx-sm-3 mb-2">
		<select class="form-control" id="status" name="status">
			<option value="">请选择状态</option>
			<option value="0" <c:if test="${article.status==0}">selected</c:if>>已发布</option>
			<option value="1" <c:if test="${article.status==1}">selected</c:if>>审核通过</option>
			<option value="-1" <c:if test="${article.status==-1}">selected</c:if>>审核不通过</option>
			<option value="3" <c:if test="${article.status==3}">selected</c:if>>禁用</option>
		</select>
	</div>
	<input type="hidden" id="pageNum" name="pageNum" value="${pageInfo.pageNum }">
	<button type="button" class="btn btn-primary mb-2" onclick="query();">查询</button>
</form>
<table class="table">
	<thead>
		<tr>
			<th scope="col"><input type="checkbox" id="chkALL"></th>
			<th scope="col">#</th>
			<th scope="col">标题</th>
			<th scope="col">作者</th>
			<th scope="col">所属频道</th>
			<th scope="col">所属分类</th>
			<th scope="col">审核状态</th>
			<th scope="col">发布时间</th>
			<th scope="col">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list }" var="item">
			<tr>
				<th scope="col"><input type="checkbox" value="${item.id }" name="chk_list"></th>
				<th scope="row">${item.id }</th>
				<td title="${item.title }">${fn:substring(item.title,0,15)}</td>
				<td>${item.nickname }</td>
				<td>${item.channel_name }</td>
				<td>${item.category_name }</td>
				<td>${item.status==1?'通过':item.status=='-1'?'未通过':item.status=='0'?'已发布':'禁用' }</td>
				<td><fmt:formatDate value="${item.created }" pattern="yyyy-MM-dd"/> </td>
				<td>
					<input type="button" class="btn btn-primary" onclick="viewArticle(${item.id})" value="查看">
					<input type="button" class="btn btn-primary" onclick="checkAlert(${item.id})" value="审核">
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div class="row">
	<!-- <div class="col-3">
		<button type="button" class="btn btn-primary mb-2" onclick="add();">添加</button>
		<button type="button" class="btn btn-primary mb-2" onclick="delAlert();">删除</button>
	</div> -->
	<div class="col-9">
		<jsp:include page="../common/page.jsp"></jsp:include>
	</div>
</div>
<div class="alert alert-success" role="alert" style="display: none"></div>

<div class="modal" tabindex="-1" role="dialog" id="checkModal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">确认框</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="checkForm">
        	<input type="hidden" id="id" name="id">
        	<input type="radio" name="status" value="1">审核通过<br>
        	<input type="radio" name="status" value="-1">审核不通过
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="check();">确认</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript" src="/public/js/checkbox.js" ></script>

<script type="text/javascript">

	function checkAlert(id){
		$("#checkForm #id").val(id);
		$("#checkModal").modal('show');
	}
	
	function check(){
		$("#checkModal").modal('hide');
		$.post("/admin/check",$("#checkForm").serialize(),function(res){
			if(res.result){
				query();
			}
		})
	}
	
	function query(){
		$("#pageNum").val(1);
		var params = $("#queryForm").serialize();
		reload(params)
	}
	
	function gotoPage(pageNum){
		$("#pageNum").val(pageNum);
		reload($("#queryForm").serialize())
	}
	
	function viewArticle(id){
		window.open('/article/detail/'+id+'.html');
	}
	
	
	
</script>

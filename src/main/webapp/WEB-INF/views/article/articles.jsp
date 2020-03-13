<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<form class="form-inline" id="queryForm">
	<div class="form-group mx-sm-3 mb-2">
		<input type="text" class="form-control" id="title" name="title" value="${article.title }" placeholder="请输入文章标题">
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
			<th scope="col">所属频道</th>
			<th scope="col">所属分类</th>
			<th scope="col">发布时间</th>
			<th scope="col">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.list }" var="item">
			<tr>
				<th scope="col"><input type="checkbox" value="${item.id }" name="chk_list"></th>
				<th scope="row">${item.id }</th>
				<td>${item.title }</td>
				<td>${item.channel_name }</td>
				<td>${item.category_name }</td>
				<td><fmt:formatDate value="${item.created }" pattern="yyyy-MM-dd"/> </td>
				<td><input type="button" class="btn btn-primary" onclick="modify(${item.id})" value="编辑"></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div class="row">
	<div class="col-3">
		<button type="button" class="btn btn-primary mb-2" onclick="add();">添加</button>
		<button type="button" class="btn btn-primary mb-2" onclick="delAlert();">删除</button>
	</div>
	<div class="col-9">
		<jsp:include page="../common/page.jsp"></jsp:include>
	</div>
</div>
<div class="alert alert-success" role="alert" style="display: none"></div>

<div class="modal" tabindex="-1" role="dialog" id="deleteModal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">确认框</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>你确认删除文章吗？</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="del();">确认删除</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript" src="/public/js/checkbox.js" ></script>

<script type="text/javascript">
	function add(){
		openPage('/article/add');
	}
	function modify(id){
		openPage('/article/add?id='+id);
	}
	
	function delAlert(){
		var ids = getCheckboxIds();
		if(ids==null || ids==""){
			$(".alert").html("请选择文章");
			$(".alert").show();
			return;
		}
		$("#deleteModal").modal('show');
	}
	
	function del(){
		var ids = getCheckboxIds();
		console.log(ids);
		$(".alert").hide();
		$("#deleteModal").modal('hide');
		$.post("/article/deleteByIds",{ids:ids},function(res){
			if(res.result){
				query();
			}
		})
	}
	
	function query(){
		var params = $("#queryForm").serialize();
		reload(params)
	}
	
	function gotoPage(pageNum){
		$("#pageNum").val(pageNum);
		query();
	}
	
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav aria-label="Page navigation example">
	<ul class="pagination">
		<c:if test="${pageInfo.hasPreviousPage }">
			<li class="page-item"><a class="page-link" href="javascript:gotoPage('${pageInfo.pageNum-1 }');">上一页</a></li>
		</c:if>
		<c:forEach items="${pageInfo.navigatepageNums }" var="item">
			<c:if test="${pageInfo.pageNum==item }">
				<li class="page-item active"><a class="page-link" href="javascript:gotoPage('${item}');">${item }</a></li>
			</c:if>
			<c:if test="${pageInfo.pageNum!=item }">
				<li class="page-item"><a class="page-link" href="javascript:gotoPage('${item}');">${item }</a></li>
			</c:if>
		</c:forEach>
		<c:if test="${pageInfo.hasNextPage }">
			<li class="page-item"><a class="page-link" href="javascript:gotoPage('${pageInfo.pageNum+1 }');">下一页</a></li>
		</c:if>
	</ul>
</nav>
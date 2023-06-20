<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>

<div class="container_animal">
<div class="container mt-6 mb-4">

	<h2 style="text-align: center; margin-top: 50px;">후원 해주신 물품</h2>

	<div style="margin-top:40px;"> <!-- 추가된 div 요소 -->
		<table class="table" style="margin-left: 50px;">
			<thead>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="items" items="${paginatedItems}">
					<tr>
						<td>${items.id}</td>
						<td><a href="/items/${items.id}">${items.title}</a></td>
						<td>${items.name}</td>
						<td>${items.createDate }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</div>

<%@ include file="../layout/AdminFooter.jsp"%>
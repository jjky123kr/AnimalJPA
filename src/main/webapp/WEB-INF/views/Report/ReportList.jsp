<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/AdminHeader.jsp"%>

<div class="container_animal">
<div class="container mt-6 mb-4">
	<a href="/AnimalReport" class="btn btn-outline-warning mb-3">글등록</a>

	<h2 style="text-align: center; margin-top: 50px;">동물 구조제보 합니다.</h2>

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
				<c:forEach var="report" items="${report.content}">
					<tr>
						<td>${report.id}</td>
						<td><a href="/User/report/${report.id}">${report.title}</a></td>
						<td>${report.name}</td>
						<td>${report.createDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</div>

<%@ include file="../layout/AdminFooter.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>



<div style="margin: 50px; width: 90%;">
	<form>
<input type="hidden" id="id" value="${report.id}">
		<h1>
			<strong>동물 제보해주세요</strong>
		</h1>

		<br>
		<h3>제목:${report.title}</h3>


		<ol class="breadcrumb bg-transparent">
			<li class="breadcrumb-item"><a href="#">동물제보</a></li>
			<li class="breadcrumb-item active" aria-current="page">작성자:${report.name}</li>
			<li class="breadcrumb-item active" aria-current="page">날짜:${report.createDate}</li>
		</ol>
		<p>${report.content}</p>

		<div style="text-align: center;">
		<c:if test="${report.member.id==principal.member.id}">
			<a href="/report/${report.id}/updateForm" class="btn btn-outline-warning">글수정</a>
			<button id="btn-delete" class="btn btn-outline-warning">글삭제</button>
		</c:if>
	<a href="javascript:void(0);" class="btn btn-outline-warning" onclick="history.back()">목록</a>

</div>

</form>
</div>
<script src="/js/Member/AnimalReport.js"></script>
<%@ include file="../layout/AdminFooter.jsp"%>
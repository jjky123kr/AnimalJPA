<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>


<form>
	<input type="hidden" id="id" value="${items.id}">
	<h1>
		<strong>후원물품</strong>
	</h1>

	<br>
	<h3>제목:${items.title}</h3>


	<ol class="breadcrumb bg-transparent">
		<li class="breadcrumb-item"><a href="#">후원물품</a></li>
		<li class="breadcrumb-item active" aria-current="page">작성자:${items.name}</li>
		<li class="breadcrumb-item active" aria-current="page">날짜:${items.createDate}</li>
	</ol>
	<p>${items.content}</p>

	<div style="text-align: center;">
	
			<a href="/itmes/${items.id}/updateForm" class="btn btn-outline-warning">글수정</a>
			<button id="btn-delete" class="btn btn-outline-warning">글삭제</button>
		
		<a href="javascript:void(0);" class="btn btn-outline-warning" onclick="history.back()">목록</a>

	</div>

</form>

<script src="/js/Admin/items.js"></script>
<%@ include file="../layout/AdminFooter.jsp"%>
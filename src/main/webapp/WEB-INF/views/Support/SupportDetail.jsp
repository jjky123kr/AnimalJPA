<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/AdminHeader.jsp"%>


		
	<div style="text-align: center;">
	<h1 style="margin-top:200px;">${support.title}</h1>
    </div>

<div class="container mt-5">

	<div>
	<input type="hidden" id="id" value="${support.id}">
		<table class="table" style="margin-top:70px;">

			<tr>
				<th>동물 타입</th>
				<td>${support.type}</td>
			</tr>

			<tr>
				<th>이름</th>
				<td>${support.name}</td>
			</tr>
			
			<tr>
				<th>설명</th>
				<td>${support.content}</td>
			</tr>
		</table>
			<div style="text-align:center;">
			<a href="/support/${support.id}/supportPay"><button class="btn btn-outline-warning" style="margin-bottom:100px;">후원하기</button></a>
			<a href="/support/${support.id}/updateForm"><button class="btn btn-outline-warning" style="margin-bottom:100px;">수정</button></a>
			<button id="btn-delete" class="btn btn-outline-warning" style="margin-bottom:100px;">삭제</button>
		</div>
	</div>
</div>


<script src="/js/Admin/Support.js"></script>

<%-- <%@ include file="../layout/AdminFooter.jsp"%> --%>
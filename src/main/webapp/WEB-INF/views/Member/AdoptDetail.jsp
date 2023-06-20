<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>


<div>
글번호:<span id="id"><i>${adopt.id}</i></span>
</div>


<h1 style="text-align: center;">입양 반려동물</h1>
<br>	
		<h2 style="text-align: center;">
			${adopt.title}
			<a href="/adopt/${adopt.id}/SurveyForm"><button class="btn btn-outline-warning" style="margin:5px;">입양신청</button></a>
		</h2>
<div class="container mt-5">
	
		<table class="table" style="margin: 0.1px;">

			<tr>
				<th>동물 타입</th>
				<td>${adopt.type}</td>
			</tr>

			<tr>
				<th>이름</th>
				<td>${adopt.name}</td>
			</tr>

			<tr>
				<th>성별</th>
				<td>${adopt.gender}</td>
			</tr>


			<tr>
				<th>종류</th>
				<td>${adopt.kind}</td>
			</tr>

			<tr>
				<th>나이</th>
				<td>${adopt.age}</td>
			</tr>

			<tr>
				<th>몸무게</th>
				<td>${adopt.kg}</td>
			</tr>

			<tr>
				<th>중성화/건강상태</th>
				<td>${adopt.health}</td>
			</tr>

			<tr>
				<th>대표사진</th>
				<td>${adopt.profileImage}</td>
			</tr>
			
			<tr>
				<th>설명</th>
				<td>${adopt.content}</td>
			</tr>
		</table>
	</div>
</div>


<script src="/js/Admin/Adopt.js"></script>

<%@ include file="../layout/AdminFooter.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/AdminHeader.jsp"%>

<form>

	<div class="container_animal">
    <div class="container mt-3 mb-4">
    <h2 style="text-align: center; margin-top: 50px;">회원정보</h2>
	<table class="table" style="margin-left: 50px;">
		<tr>
			<th>회원번호</th>
			<th>이름</th>
			<th>이메일</th>
			<th>전화번호</th>
			<th>우편번호</th>
			<th>주소</th>
			<th>상세주소</th>
			<th>회원가입일</th>
		</tr>
		
		<c:forEach var="member" items="${members}">
			<tr>
			<td>${member.id}</td>
			<td>${member.name}</td>
			<td>${member.email}</td>
			<td>${member.phon1}-${member.phon2}-${member.phon3}</td>
			<td>${member.post}</td>
			<td>${member.address1}</td>
			<td>${member.address2}</td>
			<td>${member.createDate}</td>
			</tr>
		</c:forEach>
	</table>
</div>
</div>

</form>




<%@ include file="../layout/AdminFooter.jsp"%>
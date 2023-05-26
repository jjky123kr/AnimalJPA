<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/AdminHeader.jsp"%>

<h4 style="margin-left: 50px;">작성자 :${animal.member.name}</h4>

<div style="margin-left:50px;">
<h4>글번호: <span id="id"><i>${animal.animalid}</i></span></h4>
</div>

<h1 style="text-align: center;">동물 정보</h1>

<div style="text-align: center;">
    <button class="btn btn-outline-warning" onclick="history.back()">목록</button>
    
	<c:if test="${animal.member.id==principal.member.id}">
		<a href="/animal/${animal.animalid}/updateForm" class="btn btn-outline-warning">수정</a>
		<button id="btn-delete" class="btn btn-outline-warning">삭제</button>
       </c:if>
</div>

<div class="container mt-5">
	<div class="col-9 border border-dark p-2 mx-auto">
		<table class="table" style="margin: 0.1px;">

			<tr>
				<th>동물 타입</th>
				<td>${animal.type}</td>
			</tr>

			<tr>
				<th>제목</th>
				<td>${animal.title}</td>
			</tr>

			<tr>
				<th>이름</th>
				<td>${animal.name}</td>
			</tr>


			<tr>
				<th>성별</th>
				<td>${animal.gender}</td>
			</tr>


			<tr>
				<th>종류</th>
				<td>${animal.kind}</td>
			</tr>

			<tr>
				<th>나이</th>
				<td>${animal.age}</td>
			</tr>

			<tr>
				<th>몸무게</th>
				<td>${animal.kg}</td>
			</tr>

			<tr>
				<th>중성화/건강상태</th>
				<td>${animal.neutered}</td>
			</tr>

			<tr>
				<th>대표사진</th>
				<td>${animal.content}</td>
			</tr>


		</table>
	</div>
</div>

<br>

<br>
<br>


<%@ include file="../layout/AdminFooter.jsp"%>
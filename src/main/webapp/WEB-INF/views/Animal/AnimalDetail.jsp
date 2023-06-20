<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/AdminHeader.jsp"%>


<h1 style="text-align: center;">동물 정보</h1>
<div>
<span id="id"><i>${animal.animalid}</i></span>
</div>

<div class="container mt-5">
	
		<table class="table" style="margin: 0.1px;">

			<tr>
				<th>동물 타입</th>
				<td>${animal.animalType}</td>
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
	
	<div style="text-align: center;,margin-bottom: 100px;">
    <button class="btn btn-outline-warning" onclick="history.back()">목록</button>
	 <a href="/animal/${animal.animalid}/updateForm"><button class="btn btn-outline-warning" >수정</button></a>
	<button id="btn-delete" class="btn btn-outline-warning">삭제 </button>     
</div>


<br>

<br>
<br>

<!-- board.js -->
<script src="/js/Admin/Animal.js"></script>

<%@ include file="../layout/AdminFooter.jsp"%>
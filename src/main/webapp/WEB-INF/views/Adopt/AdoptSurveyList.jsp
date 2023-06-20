<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/AdminHeader.jsp"%>

<form>

	<h2 style="margin: 100px;">입양신청 목록</h2>
	<table class="table" style="margin-bottom: 30px;">
		<tr>
			<th>글번호</th>
			<th>입양동물번호</th>
			<th>입양제목</th>
			<th>이름</th>
			<th>이메일</th>
			<th>전화번호</th>
			<th>나이</th>
			<th>주소</th>
			<th>입양처리</th>
		</tr>

		<c:forEach var="survey" items="${survey.content}">
			<tr>
				<td>${survey.id}</td>
				<td>${survey.adopt.id}</td>
				<td>${survey.adopt.title}</td>
				<td><a href="/survey/${survey.id}">${survey.name}</a></td>
				<td>${survey.email}</td>
				<td>${survey.tel}</td>
				<td>${survey.age}</td>
				<td>${survey.address}</td>
			   <td><button id="adoptionButton" onclick="completeAdoption(${survey.id})" class="btn btn-warning">${survey.adoptionStatus.getKorName()}</button></td>

			</tr>
		</c:forEach>
	</table>
</form>




<script>
function completeAdoption(id) {
  $.ajax({
    url: "/completeAdoption",
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify({ id: id }),
    success: function(response) {
      $("#adoptionButton").text("입양완료");
    },
    error: function(error) {
      console.log(error);
    }
  });
}
</script>
</script>
<%@ include file="../layout/AdminFooter.jsp"%>
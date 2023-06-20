<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>

<sec:authorize access="hasRole('USER')">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>

<main>
	<form action="/User/AdoptList">
	<!-- 검색을 하지 않았을때 리스트 -->
		<div class="row row-cols-1 row-cols-md-5 g-5" style="margin-left: 50px;">
			<c:forEach var="adopt" items="${adopt.content}">
				<div class="col">
					<div class="card" style="padding-left: 25.5px; margin-top: 100px;">
						<div class="card-img-top">
							<div style="width: 500px;">${adopt.profileImage}</div>
							<div class="card-body">
								<h5 class="card-title">
									<a href="/User/adopt/${adopt.id}">${adopt.title}</a>
								</h5>

							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>

<!-- 검색 했을 경우 :keyword="${keyword}"추가한다.-->

		<ul class="pagination justify-content-center">
			<li class="page-item"><a class="page-link" href="?page=${adopt.number-1}&keyword=${keyword}">Previous</a></li>
			<c:forEach var="i" begin="1" end="${adopt.totalPages}">
				<li class="page-item"><a class="page-link" href="?page=${i-1}">${i}</a></li>
			</c:forEach>
			<li class="page-item"><a class="page-link" href="?page=${adopt.number+1}&keyword=${keyword}">Next</a></li>
		</ul>
	
		<!-- 검색창-->
		
		<div class="search">
		 <select id="" name=""  class="select">
		 <option>제목</option>
		 <option>이름</option>
		 </select>
			<input name="keyword" type="text" class="border border-warning" placeholder="검색어 입력" 
			aria-label="search" style="height:40px; border-radius:6px;">

			<button class="btn btn-outline-warning" type="submit">
				<i class="fa-solid fa-magnifying-glass"></i>
			</button>



		</div>

	</form>
</main>

<script>
	
</script>

<%@ include file="../layout/AdminFooter.jsp"%>
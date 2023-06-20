<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/AdminHeader.jsp"%>

<main>



	<div class="row row-cols-1 row-cols-md-5 g-5" style="margin-left: 50px;">
	
		<c:forEach var="animal" items="${animals.content}">
			<div class="col">
				<div class="card" style="padding-left:25.5px; margin-top:150px;">
					<div class="card-img-top">
						<div style="width:500px;">${animal.content}</div>
						<div class="card-body">
							<h5 class="card-title"><a href="/animal/${animal.animalid}">${animal.title}</a></h5>
							
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
    </div>

	<%--페이징 처리 방식 1. --%>
	<%-- 
	<ul class="pagination justify-content-center">
  <li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
       <c:forEach var="i" begin="1" end="${animals.totalPages}">
            <li class="page-item"><a class="page-link" href="?page=${i-1}">${i}</a></li>
        </c:forEach>
  <li class="page-item"><a class="page-link" href="#">Next</a></li>
</ul>  --%>

	<%--페이징 처리 방식 2. --%>

	<ul class="pagination justify-content-center">
		<li class="page-item"><a class="page-link" href="?page=${animals.number-1}">Previous</a></li>
		<c:forEach var="i" begin="1" end="${animals.totalPages}">
			<li class="page-item"><a class="page-link" href="?page=${i-1}">${i}</a></li>
		</c:forEach>
		<li class="page-item"><a class="page-link" href="?page=${animals.number+1}">Next</a></li>
	</ul>
</main>






<%@ include file="../layout/AdminFooter.jsp"%>
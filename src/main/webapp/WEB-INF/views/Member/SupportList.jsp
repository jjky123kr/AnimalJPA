<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>

<main>
	<form>
		<div class="row row-cols-1 row-cols-md-5 g-5" style="margin-left: 50px;">
			<c:forEach var="support" items="${support.content}">
				<div class="col">
					<div class="card" style="padding-left: 25.5px; margin-top: 100px;">
						<div class="card-img-top">
							<div style="width: 500px;">${support.profileImage}</div>
							<div class="card-body">
								<h5 class="card-title">
									<a href="/User/support/${support.id}">${support.title}</a>
								</h5>

							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>

		<ul class="pagination justify-content-center">
			<li class="page-item"><a class="page-link" href="?page=${support.number-1}">Previous</a></li>
			<c:forEach var="i" begin="1" end="${support.totalPages}">
				<li class="page-item"><a class="page-link" href="?page=${i-1}">${i}</a></li>
			</c:forEach>
			<li class="page-item"><a class="page-link" href="?page=${support.number+1}">Next</a></li>
		</ul>

	</form>
</main>

<script>
	
</script>

<%@ include file="../layout/AdminFooter.jsp"%>
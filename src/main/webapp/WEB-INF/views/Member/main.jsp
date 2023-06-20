<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../layout/header.jsp"%>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<!-- Carousel -->
<div id="demo" class="carousel slide" data-bs-ride="carousel">

  <!-- Indicators/dots -->
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
    <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
    <button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
  </div>
  
  <!-- The slideshow/carousel -->
  <div class="carousel-inner"">
    <div class="carousel-item active">
      <img src="/images/bobo2.JPG" alt="Los Angeles" class="d-block" style="width:100%">
      <div class="carousel-caption">
       
      </div>
    </div>
    <div class="carousel-item">
      <img src="/images/support4.jpg" alt="Chicago" class="d-block" style="width:100%">
      <div class="carousel-caption">
      
      </div> 
    </div>
    <div class="carousel-item">
      <img src="/images/support3.jpg" alt="New York" class="d-block" style="width:100%">
      <div class="carousel-caption">
       
      </div>  
    </div>
  </div>
  
  <!-- Left and right controls/icons -->
  <button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
    <span class="carousel-control-prev-icon"></span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#demo" data-bs-slide="next">
    <span class="carousel-control-next-icon"></span>
  </button>
</div>

<div style="margin-top: 50px;,margin-left:50px;">
  <h1>AnimalAdopt 센터 동물들</h1>
</div>
<main>

	<div class="row row-cols-1 row-cols-md-5 g-4" style="margin-left: 50px;">
	
		<c:forEach var="animal" items="${animals.content}">
			<div class="col">
				<div class="card" style="padding-left:25.5px; margin-top:150px;">
					<div class="card-img-top">
						<div style="width:500px;">${animal.content}</div>
						<div class="card-body">
							<h5 class="card-title"><a href="/animal/${animal.animalid}" style="text-decoration: none;">${animal.title}</a></h5>
							
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
<br>
<br>
	<ul class="pagination justify-content-center">
		<li class="page-item"><a class="page-link" href="?page=${animals.number-1}">Previous</a></li>
		<c:forEach var="i" begin="1" end="${animals.totalPages}">
			<li class="page-item"><a class="page-link" href="?page=${i-1}">${i}</a></li>
		</c:forEach>
		<li class="page-item"><a class="page-link" href="?page=${animals.number+1}">Next</a></li>
	</ul>
</main>


</body>
</html>

<%@ include file="../layout/AdminFooter.jsp"%>
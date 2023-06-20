<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="http://code.jquery.com/jquery-latest.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<script src="https://kit.fontawesome.com/3b085ba6e6.js" crossorigin="anonymous"></script>

<title>Document</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Member/member.css">
</head>
<style type="text/css">
	* {
		margin: 0;
		padding: 0;
	}
	ul li{
		list-style: none;
	}
	a {
		text-decoration: none;
		color:#333;
	}

	#menu {
		font:bold 16px "malgun gothic";
		width:700px;
		height:50px;
		background: #ccc;
		color:black;
		line-height: 50px; 
		margin:0 auto;
		text-align: center;
	}

	.nav > ul > li {
		float:left;
		width:140px;
		position:relative;
	}
	.nav > ul > li > ul {
		width:130px;
		display:none;
		position: absolute;
		font-size:14px;
		background: white;
	}
	.nav > ul > li:hover > ul {
		display:block;
		
	}
	.nav > ul > li > ul > li:hover {
		transition: ease 1s;
		}
	</style>
<body>
<div class="contaniner">
		<div class="header">
			<h2>
				<i class="fa-solid fa-paw"></i><a href="/"> ANIMALSPOH</a>
			</h2>
				<c:choose>
				<c:when test="${empty principal}">
					<div class="nav">
						<ul>
							<li><a href="/#">Animal 동물 소개</a>
								<ul class="dropdown">
									<li><a href="/AnimalReportList">동물 제보</a></li>
								</ul>
							</li>
							<li><a href="/User/AdoptList">반려동물 입양</a>
								<ul class="dropdown">
									<li><a href="/AdoptReview/List">입양후기</a></li>
								</ul></li>
								<li><a href="/User/SupportList">후원동물</a>
								<ul class="dropdown">
								<li><a href="/ItemsList">후원물품</a></li>
								</ul></li>
							<li><a href="/auth/LoginForm">로그인</a></li>
							<li><a href="/auth/JoinForm">회원가입</a></li>
						</ul>
					</div>
				</c:when>

				<c:otherwise>
					<div class="nav">
						<ul>
							<li><a href="/#">Animal 동물 소개</a>
								<ul class="dropdown">
										<li><a href="/AnimalReportList">동물 제보</a></li>
								</ul>
							</li>
								<li><a href="/User/AdoptList">반려동물 입양</a>
								<ul class="dropdown">
										<li><a href="/User/AdoptReview">입양후기</a></li>
								</ul></li>
									<li><a href="/User/SupportList">후원동물</a>
								<ul class="dropdown">
										<li><a href="/ItemsList">후원물품</a></li>
								</ul></li>
							<li><a href="/logout">로그아웃</a></li>
						</ul>
					</div>
				</c:otherwise>
			</c:choose>

		</div>
	</div>
	


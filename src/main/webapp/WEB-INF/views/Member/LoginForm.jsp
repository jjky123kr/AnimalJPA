<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<style>
.container{
border: 2px solid orange ;
border-radius: 10px;
width: 50%;
height:500px;
margin-bottom: 100px;
}
h2{
margin-top:70px;
font-size: 45px;
}


</style>

<div class="container">
<h2>Login</h2>
	<form action="/auth/loginProc" method="post">
		<div class="mb-3 mt-3">
			<label for="username" class="form-label">ID:</label> 
			<input type="text" class="form-control" id="username" placeholder="Username" name="username">
		</div>
		<div class="mb-3">
			<label for="password" class="form-label">Password: </label> 
			<input type="password" class="form-control" id="password" placeholder="Password" name="password">
		</div>


		<div class="form-check mb-3">
			<label class="form-check-label"> 
			<input class="form-check-input" type="checkbox" name="remember"> Remember me
			</label>
		</div>
		<div class="d-grid gap-2 col-5 mx-auto">
			<button class="btn btn-warning btn-block" type="submit">로그인</button>
			<br>
			<a href="https://kauth.kakao.com/oauth/authorize?client_id=ef61f72fe4a277bad6ae8c88f03ec6c6
			&redirect_uri=http://localhost:8088/auth/kakao/callback&response_type=code">
	         <img width="300px;" src="/images/kakao_login_large_wide.png" /></a>
	         <a href="/oauth2/authorization/google"></a>
		</div>
			
		</div>


	</form>
</div>
<%@ include file="../layout/AdminFooter.jsp"%>
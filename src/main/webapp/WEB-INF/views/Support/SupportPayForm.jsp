<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/AdminHeader.jsp"%>
<!DOCTYPE html>
<html>
<head>
<!-- jQuery -->
<!-- 제이쿼리 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<title>Half Page Image</title>
<style>
body {
	margin: 0;
	padding: 0;
}

.image-container {
	align-items:flex-end;
	margin-top: 100px;
}

.image-container img {
    object-fit: cover;
	width:1600px;;
	height:400px;
	margin-left:45px;
	
}

.content-box {
	background-color: #ebebeb;
	margin-top:50px;
	padding: 20px;
	width: 50%;
	margin-left: 100px;
}

.supportinfo {
  
	background-color: white;
	margin-top: 20px;
	padding: 20px;
	margin-left:150px;
}

.info{
	margin-left:150px;
	margin-bottom: 20px;
}

.info2{
	margin-left:150px;
}





@media ( max-width : 767px) {
	.image-container {
		flex-direction:column;
	}
	.image-container img, .content-box {
		width: 100%;
	}
}
</style>
</head>
<body>
	<nav>
		<!-- nav 요소의 내용 -->
	</nav>

	<div class="image-container">
		<img src="/images/support.jpg" alt="Your Image">
		<div class="content-box">
			<h4>후원하기</h4>
			<p>
				"모든 생명은 보호받고 존중받을 권리가 있습니다"<br> 사람과 동물이 생태적·윤리적 조화를 이루며 살아가는 세상을 만들기 위해 다방면에서 활동을 하겠습니다.
			</p>
		</div>
	</div>
	<hr>

	<div class="info">
		<h4>개인정보</h4>
		<strong >구분 </strong>
		<button class="btn btn-outline-warning">개인</button>
		<button class="btn btn-outline-warning">단체</button>
	</div>


	<div class="info2">
		<div>
			<label for="name">이름</label> 
			<input type="text" size="20" name="name" id="name">
		</div>

		<div>
			<label for="tel">휴대폰</label> 
			<input type="text" size="20" name="tel" id="tel">
		</div>

		<div>
			<label for="email">이메일</label> 
			<input type="text" size="20" name="email" id="email">
		</div>
	</div>
	<hr>
	
	<div class="supportinfo">
		<h4>후원정보</h4>
		<br> <span>후원항목: ${support.name}</span> <br>
		<br> <strong class="pay">후원금액 </strong> <br>
		<br>
		<input type="button" class="btn btn-outline-warning" id="money" name="money" value="20000">
		<input type="button" class="btn btn-outline-warning" id="money" name="money" value="40000">
		<input type="button" class="btn btn-outline-warning" id="money" name="money" value="50000">
		<input type="button" class="btn btn-outline-warning" id="money" name="money" value="100000">
        <input type="tel" class="btn btn-outline-warning"  id="direct_money" name="direct_money" maxlength="11" placeholder="직접입력" autocomplete="off">

		<div style="margin-top: 20px;">
		<button class="btn btn-outline-warning" id="request_pay">결제하기</button>
        </div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	$("#request_pay").click(function(){
		var money = $("#money").val();
		console.log(money)
		$.ajax({
			url:'/api/kakaopay',
			dataType:'json',
			success:function(data){
				var box = data.next_redirect_pc_url;
				window.open(box);
			},
			error:function(error){
				alert(error);
			}
		});
	});
});

</script>
</html>
<%@ include file="../layout/footer.jsp"%>

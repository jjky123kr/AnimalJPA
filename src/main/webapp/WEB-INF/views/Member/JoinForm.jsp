<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

 
<!--[우편 번호]-->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
function openDaumPostcode() {
	new daum.Postcode({
		oncomplete : function(data) {				
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
			// 우편번호와 주소 정보를 해당 필드에 넣고, 커서를 상세주소 필드로 이동한다.
			document.getElementById('post').value = data.zonecode;
			document.getElementById('address1').value = data.address;				
		}
	}).open();
}		
</script>



<main>
	<form action="/user/join" method="post"  >
		<table>

			<tr>
				<td class="col1">아이디</td>
				<td class="col2">
				<input type="text" id="username" name="username">
				<button type="button" class="btn btn-dark" id="check">중복검사</button>
				</td>
			</tr>

			<tr>
				<td class="col1">이름</td>
				<td class="col2">
				<input type="text" id="name" name="name" size="10"></td>
			</tr>

			<tr>
				<td class="col1">비밀번호</td>
				<td class="col2">
				<input type="password" id="password" name="password" size="20"></td>
			</tr>
			
			<tr>
			 <td class="col1">비밀번호 확인</td>
            <td class="col2">
            <input type="password" id="pwcheck" name="pwcheck" size="20"></td>
			</tr>
			
			
			<tr>
				<td class="col1">이메일</td>
                 <td class="col2"><input type="text" id="email" name="email" size="20"></td>
			</tr>


			<tr>
				<td class="col1">생년월일</td>
				<td class="col2"><input type="date" id="brithday" name="brithday" size="20"></td>
			</tr>


			<tr>
			<td class="col1">휴대폰</td>
			<td class="col3" ><input type="text" id="phon1" name="phon1" size="3">-
			                             <input type="text" id="phon2" name="phon2" size="3">-
			                             <input type="text" id="phon3" name="phon3" size="3"></td>
			</tr>

			<tr>
				<td class="col1">우편번호</td>
				<td><input type="text" class="post_area" name="post" id="post" size="5"  readonly
						onclick="post_search()"> 
				<input type="button" value="우편번호 검색" class="post_search_btn" onclick="openDaumPostcode()"></td>
			</tr>

			<tr>
				<td class="col1">주소</td>
				<td class="col2"><input type="text" name="address1" id="address1" size="50" readonly onclick="post_search()"></td>
			</tr>

			<tr>
				<td class="col1">상세주소</td>
				<td class="col2"><input type="text" name="address2" id="address2" size="50" ></td>
			</tr>

		</table>

	</form>
	
	<!-- 회원가입 시 버튼으로 js 실행 id="btn-save" 호출-->
	<br>
	<div class="col text-center" >
	<button id="btn-save" class="btn btn-outline-warning" onclick="check();">회원가입</button>
	<button type="reset" class="btn btn-outline-warning">가입 취소</button>
     </div>
</main>
<!-- user.js 실행 코드 -->
<!-- <script src="/js/Member/User.js"></script>
 -->
<script src="/js/Member/Member.js"></script>

<%@ include file="../layout/footer.jsp"%>

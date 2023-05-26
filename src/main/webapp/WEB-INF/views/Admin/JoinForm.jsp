<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/AdminHeader.jsp"%>


<main>
	<form action="/user/join" method="post"  >
		<table>

			<tr>
				<td class="col1">아이디</td>
				<td class="col2">
				<input type="text" id="username" name="username">
				</td>
			</tr>

			<tr>
				<td class="col1">부서명</td>
				<td class="col2">
				<input type="text" id="name" name="name" size="10"></td>
			</tr>

			<tr>
				<td class="col1">비밀번호</td>
				<td class="col2">
				<input type="password" id="password" name="password" size="20"></td>
			</tr>
						
			
			<tr>
				<td class="col1">이메일</td>
                 <td class="col2"><input type="text" id="email" name="email" size="20"></td>
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

<script src="/js/Admin/Member.js"></script>



<%@ include file="../layout/AdminFooter.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>

<div>
	글번호:<span id="id"><i>${review.id}</i></span> 글작성자:<span id="member">${review.member.name}</span>
</div>

<div style="text-align: center;">
<h1>${review.title}</h1>

</div>

<div>
${review.content}
</div>


<div style="text-align: center;">
	<c:if test="${review.member.id==principal.member.id}">
		<a href="/review/${review.id}/updateForm"  class="btn btn-outline-warning">글수정</a>
		<button id="btn-delete"  class="btn btn-outline-warning">글삭제</button>
       </c:if>
		<button   class="btn btn-outline-warning" onclick="history.back()">목록</button>
	</div>





<script src="/js/Member/adoptReview.js"></script>
<%@ include file="../layout/AdminFooter.jsp"%>
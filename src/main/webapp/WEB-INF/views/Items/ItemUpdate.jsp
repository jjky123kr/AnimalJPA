<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/AdminHeader.jsp"%>

<div class="wrapper" style="margin: 50px; width: 90%;">
	<form>
	<input type="hidden" id="id" value="${itmes.id}">
		<div>
			<h2 style="margin-top: 50px;">
				<strong>후원물품 게시판</strong>
				<button id="btn-update" class="btn btn-dark" style="margin: 5px;">수정</button>
			</h2>
		</div>

		<div style="margin: 80px;">
			<div>
				<label for="title">제목</label> 
				<input type="text" id="title" name="title" class="form-control" value="${itmes.title}">
			</div>
			<br>
			<div>
				<label for="name">성함</label>
				 <input type="text" id="name" name="name" class="form-control" value="${itmes.name}">
			</div>

			<div>
			    <label for="noticeOption">어떤글인지 선택해주세요</label>
				<select class="select" name="noticeOption" id="noticeOption">
					<option value="notice">공지 글</option>
					<option value="notice">일반 글</option>
				</select>
			</div>

			<br>
			<div>
				<label for="profileImage">내용에 이미지와 글 작성 가능합니다.</label>
				<textarea class="summernote2" id="content" name="content" placeholder="이미지 첨부가능">${itmes.content}</textarea>
			</div>
		</div>
	</form>
</div>

<!-- 썸머노트 구현 업로드 DB저장 -->
<script>
	$('.summernote2').summernote({
		tabsize : 2,
		height : 300,
		minHeight : null,
		maxHeight : null,
		focus : true,
		callbacks : {
			onImageUpload : function(files, editor, welEditable) {
				for (var i = 0; i < files.length; i++) {
					sendFile(files[i], this);
				}
			}
		}
	});

	function sendFile(file, el) {
		var form_data = new FormData();
		form_data.append('file', file);
		$.ajax({
			data : form_data,
			type : "POST",
			url : '/image',
			cache : false,
			contentType : false,
			enctype : 'multipart/form-data',
			processData : false,
			success : function(url) {
				$(el).summernote('insertImage', url, function($image) {
					$image.css('width', "100%");
				});
			}
		});
	}
</script>
<script src="/js/Admin/items.js"></script>
<%@ include file="../layout/AdminFooter.jsp"%>
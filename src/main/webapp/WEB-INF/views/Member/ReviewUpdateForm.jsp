<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>

<div>
	글번호:<span id="id"><i>${review.id}</i></span> 글작성자:<span id="member">${review.member.name}</span>
</div>

<input type="hidden" id="id" value="${review.id}">

<div style="text-align: center;">
<label for="title">제목:</label>
<input type="text" id="title" name="title" class="form-control" value="${review.title}">		
</div>

<br><br>

<div>
<textarea id="conent" name="content" class="summernote2">${review.content}</textarea>                                 
</div>

<br><br><br>

<div style="text-align: center;">
		<button id="btn-update" class="btn btn-outline-warning" >글수정</button>
		<button   class="btn btn-outline-warning" onclick="history.back()">목록</button>
</div>

<!-- 썸머노트 구현 업로드 DB저장 -->



<script>
$('.summernote1').summernote({
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
<script src="/js/Member/adoptReview.js"></script>
<%@ include file="../layout/AdminFooter.jsp"%>
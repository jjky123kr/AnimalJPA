<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>



<div class="wrapper" style="margin:50px; width:90%;">
	<form>
	<div>
		<h2 style="margin-top:50px;">
			<strong>입양후기 등록</strong>
			<button id="btn-save" class="btn btn-dark" style="margin:5px;">등록</button>
		</h2>
    </div>
    
   <div style="margin: 80px;"> 
		<div>
		<label for="title">제목</label>
		<input type="text" id="title" name="title" class="form-control">		
		</div>
	<br>	
		<div>
		<label for="name">이름</label>
		<input type="text" id="name" name="name" class="form-control">		
		</div>
	<br>	
		<div>
		<label for="profileImage">대표이미지</label>
		<textarea class="summernote1" id="profileImage" name="profileImage" ></textarea>	
		</div>
	<br>	
		<div>
		<label for="profileImage">내용</label>
		<textarea class="summernote2" id="content" name="content" placeholder="이미지 첨부가능"></textarea>	
		</div>
   </div>
	</form>
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






<script src="js/Member/adoptReview.js"></script>
<%@ include file="../layout/AdminFooter.jsp"%>
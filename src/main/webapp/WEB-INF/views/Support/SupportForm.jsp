<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/AdminHeader.jsp"%>


<div class="wrapper" style="margin-left:100px">
	<form>
	<div style="margin-right:75%;">
		<h2 style="margin-top:100px;">
		
			<strong>후원동물 등록</strong>
			<button id="btn-save" class="btn btn-dark" style="margin:5px;">등록</button>
		</h2>
    </div>
		<table class="table" style="margin-top:70px;" >			
	
			<tr>
				<th>동물 타입</th>
				<td><select class="select" name="type" id="type">
						<option value="DOG">강아지</option>
						<option value="CAT">고양이</option>
				</select></td>
			</tr>		

			<tr>
				<th>제목</th>
				<td><input type="text" id="title" name="title" size="20px"></td>
			</tr>

			<tr>
				<th>이름</th>
				<td><input type="text" id="name" name="name" size="20px"></td>
			</tr>

			<tr>
				<th>대표사진</th>
				<td><textarea class="summernote2" rows="2" id="profileImage" name="profileImage"></textarea></td>
			</tr>
			
			<tr>
				<th>설명</th>
				<td><textarea class="summernote1" rows="2" id="content" name="content"></textarea></td>
			</tr>

		</table>


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

<script src="/js/Admin/Support.js"></script>

<%@ include file="../layout/AdminFooter.jsp"%>

















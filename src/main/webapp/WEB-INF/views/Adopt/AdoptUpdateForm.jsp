<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/AdminHeader.jsp"%>



<div style="text-align: center;">
	<h1 style="margin-top:200px;">${adopt.title}</h1>
</div>

<div class="container mt-5">
	<div>
		<form>
		<input type="hidden" id="id" value="${adopt.id}">
			<table class="table">			
				<tr>
					<th><label>동물 타입</label></th>
					<td><input value="${adopt.type}" type="text" id="type" name="type" size="20px" readonly="readonly"></td>
				</tr>

				<tr>
					<th><label>제목:</label></th>
					<td><input value="${adopt.title}" type="text" id="title" name="title" size="20px" readonly="readonly"></td>
				</tr>

				<tr>
					<th><label>이름:</label></th>
					<td><input value="${adopt.name}" type="text" id="name" name="name" size="20px" readonly="readonly"></td>
				</tr>


				<tr>
					<th><label>성별:</label></th>
					<td><input value="${adopt.gender}" type="text" id="gender" name="gender" size="20px" readonly="readonly"></td>
				</tr>


				<tr>
					<th><label>종류:</label></th>
					<td><input value="${adopt.kind}" type="text" id="kind" name="kind" size="20px" readonly="readonly"></td>
				</tr>

				<tr>
					<th><label>나이:</label></th>
					<td><input value="${adopt.age}" type="text" id="age" name="age" size="20px"></td>
				</tr>

				<tr>
					<th><label>몸무게:</label></th>
					<td><input value="${adopt.kg}" type="text" id="kg" name="kg" size="20px"></td>
				</tr>

				<tr>
					<th><label>성격:</label></th>
					<td><input value="${adopt.personality}" type="text" id="personality" name="personality" size="20px"></td>
				</tr>

				<tr>
					<th><label>중성화/건강상태:</label></th>
					<td><input value="${adopt.health}" type="text" id="health" name="health" size="20px"></td>
				</tr>


				<tr>
					<th><label>대표사진</label></th>
					<td><textarea class="form-control summernote1" rows="3" id="content" name="content" readonly="readonly">${adopt.profileImage}</textarea></td>
				</tr>
				
				<tr>
				<th>설명</th>
				<td><textarea class="form-control summernote2" rows="3" id="content" name="content" readonly="readonly">${adopt.content}</textarea></td>
			</tr>
			</table>
		</form>
		<div style="text-align: center;">
			<button id="btn-update" class="btn btn-warning" style="margin-bottom: 100px;" >수정</button>
			
		</div>
	</div>
</div>

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

<script src="/js/Admin/Adopt.js"></script>

<%@ include file="../layout/AdminFooter.jsp"%>
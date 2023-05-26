<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/AdminHeader.jsp"%>


<div class="wrapper" style="margin-left: 100px">
<div style="text-align: center;">
	</div>
	<form>
	<input type="hidden" id="id" value="${animal.animalid}">
	<h2><strong>동물정보 수정</strong>
	<button id="btn-save" class="btn btn-dark" style="margin-left:20px;">수정</button>
	</h2>
	
		<table class="table">
			<tr>
				<th><label>동물 타입</label></th>
				<td><input value="${animal.type}" type="text" id="type" name="type" size="20px" readonly="readonly">
				</td>
			</tr>
			
			<tr>
				<th><label>제목:</label></th>
				<td><input value="${animal.title}" type="text" id="title" name="title" size="20px" readonly="readonly"></td>
			</tr>

			<tr>
				<th><label>이름:</label></th>
				<td><input value="${animal.name}" type="text" id="name" name="name" size="20px" readonly="readonly"></td>
			</tr>


			<tr>
				<th><label>성별:</label></th>
				<td><input value="${animal.gender}" type="text" id="gender" name="gender" size="20px" readonly="readonly"></td>
			</tr>

            
			<tr>
				<th><label>종류:</label></th>
				<td><input value="${animal.kind}"  type="text" id="kind" name="kind" size="20px" readonly="readonly" ></td>
			</tr>
			
			<tr>
				<th><label>나이:</label></th>
				<td><input value="${animal.age}"  type="text" id="age" name="age" size="20px"></td>
			</tr>
			
			<tr>
				<th><label>몸무게:</label></th>
				<td><input value="${animal.kg}"  type="text" id="kg" name="kg" size="20px"></td>
			</tr>
			
			<tr>
				<th><label>성격:</label></th>
				<td><input  value="${animal.personality}" type="text" id="personality" name="personality" size="20px"></td>
			</tr>

			<tr>
				<th><label>중성화/건강상태:</label></th>
				<td><input value="${animal.neutered}" type="text" id="neutered" name="neutered" size="20px"></td>
			</tr>

			
			<tr>
				<th><label>대표사진</label></th>
				<td><textarea class="form-control summernote" rows="3" id="content" name="content">${animal.content}</textarea></td>
			</tr>				
			
		</table>
	</form>	
</div>

<script>
	$('.summernote').summernote({
		tabsize : 2,
		height : 300,
		minHeight : null,
		maxHeight : null,
		focus:true,
		callbacks:{
			onImageUpload:function(files,editor,welEditable){
				for(var i=0; i<files.length; i++){
					sendFile(files[i],this);
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



<!-- board.js -->
<script src="/js/Admin/board.js"></script>

<%@ include file="../layout/AdminFooter.jsp"%>



















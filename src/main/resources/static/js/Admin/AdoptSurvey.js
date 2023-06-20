let index={
		init:function(){
		$("#btn-save").on("click", () => {
		 this.save();
		  });
		$("#btn-delete").on("click", () => {
			 this.findById();
		});
		
		},
		// 입양신청서 등록
		save:function(){	
			let data={
					adoptId:$("#adoptId").val(),
					name:$("#name").val(),
					sex:$("#sex").val(),
					age:$("#age").val(),
					tel:$("#tel").val(),
					email:$("#email").val(),
					atime:$("#Atime").val(),
					address:$("#Address").val(),
					job:$("#job").val(),
					wedding:$("#wedding").val(),
					q1:$("#q1").val(),
					q2:$("#q2").val(),
					q3:$("#q3").val(),
					q4:$("#q4").val(),
					q5:$("#q5").val(),
					q6:$("#q6").val(),
					q7:$("#q7").val(),
					q8:$("#q8").val(),
					q9:$("#q9").val(),
					q10:$("#q10").val(),
					q11:$("#q11").val(),
					q12:$("#q12").val()				
			};
            
		   console.log(data);
					
		 	$.ajax({
		 		
	    		 type : "POST",
	    		 url:`/api/AdoptSurvey/${data.adoptId}`,
	    		 data:JSON.stringify(data), 
	    		 contentType:"application/json;charset=utf-8", 													
	    		 dataType:"json"
	    	}).done(function(resp){
	    		alert("설문조사 완료되었습니다.");    		
	 	    	location.href=`/admin`; 
	    		
	    	}).fail(function(error){ // 에러
	            alert(JSON.stringify(error));    		
	    	});  			
			
		},	
		
		// 입양신청 삭제
		findById:function(){	
			let id= $("#id").val();
				
		  if(confirm("정말로 삭제하시겠습니까?")){	    		
	    		$.ajax({
		    		 type:"DELETE",
		    		 url:"/api/AdoptSurvey/"+id,// 컨트롤 주소 값 ,id값을 넘겨준다.
		    		 dataType:"json"								
		    	}).done(function(resp){
		    		alert("글삭제가 완료 되었습니다.");    		
		    		location.href="/admin";     		
		    	}).fail(function(error){ // 에러
		            alert(JSON.stringify(error));    		
		    	}); 	
	    		
	    	}else{
	    		alert("삭제가 취소 되었습니다. ")
	    	}
	    },
		
		
	
}
index.init();
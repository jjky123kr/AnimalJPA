let index={
		  init: function() {
			    $("#btn-save").on("click", () => {
			      this.save();
			    });
			    
			    $("#btn-update").on("click",()=>{ 											
					this.update();
				});
			    $("#btn-delete").on("click",()=>{ 	
					this.deleteById();
				}); 
},

// 제보 등록
save:function(){
	
	var data={
			 title: $("#title").val(),
		      name: $("#name").val(),
		      content: $("#content").val()
	  };
	 console.log(data);

	 $.ajax({
		 type : "POST",
		 url:"/api/Report",
		 data:JSON.stringify(data), 
		 contentType:"application/json;charset=utf-8", 													
		 dataType:"json"
	}).done(function(resp){
		alert("후기 등록되었습니다.");    		
		location.href="/main"; 
		
	}).fail(function(error){ // 에러
        alert(JSON.stringify(error));    		
	});
	 
  },

  // 제보 수정
  update:function(){
		
		var data={
				 title: $("#title").val(),
			      name: $("#name").val(),
			      content: $("#content").val()
		  };
		
		let id= $("#id").val();
		
		 console.log(data);

		 $.ajax({
			 type :"PUT",
			 url:"/api/Report/"+id,
			 data:JSON.stringify(data), 
			 contentType:"application/json;charset=utf-8", 													
			 dataType:"json"
		}).done(function(resp){
			alert("수정되었습니다.");    		
			location.href="/AnimalReportList"; 
			
		}).fail(function(error){ // 에러
	        alert(JSON.stringify(error));    		
		});
		 
	  },
	  
	  // 삭제
	  deleteById: function() { 
		  
	    	let id= $("#id").val();// 글작성 id 텍스트	 	
	    	
	    	if(confirm("정말로 삭제하시겠습니까?")){	    		
	    		$.ajax({
		    		 type:"DELETE",
		    		 url:"/api/Report/"+id,// 컨트롤 주소 값 , id값을 넘겨준다.
		    		 dataType:"json"								
		    	}).done(function(resp){
		    		alert("글삭제가 완료 되었습니다.");    		
		    		location.href="/AnimalReportList";     		
		    	}).fail(function(error){ // 에러
		            alert(JSON.stringify(error));    		
		    	}); 	
	    		
	    	}else{
	    		alert("삭제가 취소 되었습니다. ")
	    	}
	    },

}

index.init();
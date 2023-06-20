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

save:function(){
	
	var data={
			 title: $("#title").val(),
		      name: $("#name").val(),
		      profileImage:$("#profileImage").val(),
		      content: $("#content").val()
	  };
	 console.log(data);

	 $.ajax({
		 type : "POST",
		 url:"/api/Review",
		 data:JSON.stringify(data), 
		 contentType:"application/json;charset=utf-8", 													
		 dataType:"json"
		}).done(function(resp) {
    	    if (resp.status === 200) {
    	        alert("회원가입이 완료되었습니다.");
    	        location.href = "/main";
    	    } else if (resp.status === 400) {
    	        alert("회원가입에 실패했습니다. 에러 메시지: " + resp.message);
    	    }
    	}).fail(function(error) {
    	    alert(JSON.stringify(error));
    	});
    },

  // 후기 수정
  update:function(){
		
		var data={
				 title: $("#title").val(),
			     content: $("#content").val()
		  };
		
		let id= $("#id").val();
		
		 console.log(data);

		 $.ajax({
			 type :"PUT",
			 url:"/api/Review/"+id,
			 data:JSON.stringify(data), 
			 contentType:"application/json;charset=utf-8", 													
			 dataType:"json"
		}).done(function(resp){
			alert("후기 수정되었습니다.");    		
			location.href="/User/AdoptReview"; 
			
		}).fail(function(error){ // 에러
	        alert(JSON.stringify(error));    		
		});
		 
	  },
	  // 입양후기 삭제
	  deleteById: function() { 
		  
	    	let id= $("#id").text();// 글작성 id 텍스트	 	
	    	
	    	if(confirm("정말로 삭제하시겠습니까?")){	    		
	    		$.ajax({
		    		 type:"DELETE",
		    		 url:"/api/Review/"+id,// 컨트롤 주소 값 , id값을 넘겨준다.
		    		 dataType:"json"								
		    	}).done(function(resp){
		    		alert("글삭제가 완료 되었습니다.");    		
		    		location.href="/";     		
		    	}).fail(function(error){ // 에러
		            alert(JSON.stringify(error));    		
		    	}); 	
	    		
	    	}else{
	    		alert("삭제가 취소 되었습니다. ")
	    	}
	    },

}

index.init();
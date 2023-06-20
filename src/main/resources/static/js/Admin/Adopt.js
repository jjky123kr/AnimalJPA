let index = {
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
    
    $("#btn-write").on("click",()=>{ 	
		this.write();
	}); 
    
    },
  
  // 동물 입양 정보 등록
  save: function() {
  
    var data = {
      type: $("#type").val(),
      title: $("#title").val(),
      name: $("#name").val(),
      gender: $("#gender").val(),
      kind: $("#kind").val(),
      age: $("#age").val(),
      kg: $("#kg").val(),
      health: $("#health").val(),
      personality: $("#personality").val(),
      profileImage:$("#profileImage").val(),
      content: $("#content").val()
    };
 
	 console.log(data);

	 $.ajax({
		 type : "POST",
		 url:"/api/Adopt",
		 data:JSON.stringify(data), 
		 contentType:"application/json;charset=utf-8", 													
		 dataType:"json"
		}).done(function(resp) {
    	    if (resp.status === 200) {
    	        alert("입양등록 완료되었습니다.");
    	        location.href = "/AdoptList";
    	    } else if (resp.status === 400) {
    	        alert("입양등록 실패했습니다. 에러 메시지: " + resp.message);
    	    }
    	}).fail(function(error) {
    	    alert(JSON.stringify(error));
    	});
    },
  
  // 수정
  update: function() {	  
	   
		let id = $("#id").val();	
		
	    let data = {
	      type: $("#type").val(),
	      title: $("#title").val(),
	      name: $("#name").val(),
	      gender: $("#gender").val(),
	      kind: $("#kind").val(),
	      age: $("#age").val(),
	      kg: $("#kg").val(),
	      health: $("#health").val(),
	      personality: $("#personality").val(),
	      profileImage:$("profileImage").val(),
	      content: $("#content").val()
	    };
	 
	    console.log(id);
   	    console.log(data);

		 $.ajax({
			 type :"PUT",
			 url:"/api/Adopt/"+id,
			 data:JSON.stringify(data), 
			 contentType:"application/json;charset=utf-8", 													
			 dataType:"json"
		}).done(function(resp){
			alert("입양 수정 되었습니다.");    		
			location.href="/AdoptList"; 
			
		}).fail(function(error){ // 에러
	        alert(JSON.stringify(error));    		
		});
		 
	  },
	  
	// 삭제

	  deleteById: function() {  	    	
	    	let id= $("#id").text();// 글작성 id 텍스트	 	
	    	
	    	if(confirm("정말로 삭제하시겠습니까?")){	    		
	    		$.ajax({
		    		 type:"DELETE",
		    		 url:"/api/Adopt/"+id,// 컨트롤 주소 값 , id값을 넘겨준다.
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
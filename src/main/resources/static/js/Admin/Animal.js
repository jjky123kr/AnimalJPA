let index={
		init:function(){
			$("#btn-save").on("click",()=>{ 											
				this.save();
			}); 
			
			$("#btn-update").on("click",()=>{ 											
				this.update();
			}); 
			
			$("#btn-delete").on("click",()=>{ // function(){},()=>{} this 를
				// 바인딩하기 위해서
           this.deleteById();
           }); // btn-delete 클릭시 아래가 실행 된다.
			
		},
		
   // 동물 정보 등록
    save:function(){
    	// alert("user의 save함수 호출됨");
    	 var data={		
    		   animalType:$("#animalType").val(),
    		   title:$("#title").val(),          
    		   name:$("#name").val(),            
    		   gender:$("#gender").val(),         
    		   kind:$("#kind").val(),            
    		   neutered:$("#neutered").val(),     
    		   age:$("#age").val(),               
    		   kg:$("#kg").val(),
    		   personality:$("#personality").val(),
    		   content:$("#content").val(),   		    		  
    	};
    	   
    	 console.log(data);
    	
    	$.ajax({
    		 type : "POST",
    		 url:"/api/Animal",
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
    
    // 동물 정보 수정
    update:function(){
    	 alert("user의 update함수 호출됨");   
    	 
    	let id = $("#id").val();
    	
    	 let data={
    		   animalType:$("#animalType").val(),  
    		   title:$("#title").val(),          
    		   name:$("#name").val(),            
    		   gender:$("#gender").val(),         
    		   kind:$("#kind").val(),            
    		   neutered:$("#neutered").val(),     
    		   age:$("#age").val(),               
    		   kg:$("#kg").val(),
    		   personality:$("#personality").val(),
    		   content:$("#content").val()
    		  
    	};
    	 console.log(id);
    	 console.log(data);
    	
    	$.ajax({
    		 type:"PUT",
    		 url:"/api/Animal/"+id,
    		 data:JSON.stringify(data), 
    		 contentType:"application/json;charset=utf-8", 													
    		 dataType:"json"
    	}).done(function(resp) {
    	    if (resp.status === 200) {
    	        alert("수정 완료되었습니다.");
    	        location.href = "/main";
    	    } else if (resp.status === 400) {
    	        alert("수정에 실패했습니다. 에러 메시지: " + resp.message);
    	    }
    	}).fail(function(error) {
    	    alert(JSON.stringify(error));
    	});
    },
    
    // 동물 정보 삭제
    deleteById:function(){
    	
    	alert("animal의 delete함수 호출됨");
    	
    	let id= $("#id").text();// 글작성 id 텍스트
    	
    	$.ajax({
   		 type:"DELETE",
   		 url:"/api/Animal/"+id,// 컨트롤 주소 값 , id값을 넘겨준다.
   		 dataType:"json"// 요청을 서버로 해서 응답이 왔을때 기본으로 모든적이 문자열(생긴것이
							// json)이라며=>javascript 오브젝트로변경
   	}).done(function(resp){
   		alert("글삭제가 완료 되었습니다.");    		
   		location.href="/admin";     		
   	}).fail(function(error){ // 에러
           alert(JSON.stringify(error));    		          
   	 }); 
 
    },
   
}

index.init();
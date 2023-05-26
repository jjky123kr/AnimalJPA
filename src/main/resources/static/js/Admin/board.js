let index={
		init:function(){
			$("#btn-save").on("click",()=>{ 											
				this.save();
			}); 		
			$("#btn-delete").on("click",()=>{ 
				
            this.deleteByid();
            }); 
			$("#btn-update").on("click",()=>{ 											
				this.save();
			}); 		
			
		},
		
    save:function(){
    	// alert("user의 save함수 호출됨");
    	 var data={
    		   type:$("#type").val(),        
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
    	
    	$.ajax({
    		 type:"POST",
    		 url:"/api/Animal",
    		 data:JSON.stringify(data), 
    		 contentType:"application/json;charset=utf-8", 													
    		 dataType:"json"
    	}).done(function(resp){
    		alert("글작성이 완료되었습니다.");    		
    		location.href="/admin"; 
    		
    	}).fail(function(error){ // 에러
            alert(JSON.stringify(error));    		
    	});   
    },
    
    update:function(){
    	// alert("user의 save함수 호출됨");
    	
    	let id = $("#id").val();
    	
    	 let data={
    		   type:$("#type").val(),        
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
    	}).done(function(resp){
    		alert("글수정이 완료되었습니다.");    		
    		location.href="/admin"; 
    		
    	}).fail(function(error){ // 에러
            alert(JSON.stringify(error));    		
    	});   
    },
}
index.init();
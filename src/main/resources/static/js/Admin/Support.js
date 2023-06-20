let index={
    init:function(){
    	$("#btn-save").on("click",()=>{
    		this.save();
    	});  
    	
    	$("#btn-update").on("click",()=>{
    		this.update();
    	});  
    	$("#btn-delete").on("click",()=>{
    		this.findById();
    	});  
    	
    	
    },
    
    save:function(){
    	var data={		
    			type:$("#type").val(),
    			title:$("#title").val(),
    			name:$("#name").val(),
    			profileImage:$("#profileImage").val(),
    			content: $("#content").val()
    	};
    	
    	 console.log(data);
         
    	 $.ajax({
    		 type :"POST",
    		 url:"/api/Support",
    		 data:JSON.stringify(data), 
    		 contentType:"application/json;charset=utf-8", 													
    		 dataType:"json"
    	}).done(function(resp){
    		alert("후원동물이 등록되었습니다.");    		
    		location.href="/SupportList"; 
    		
    	}).fail(function(error){ // 에러
            alert(JSON.stringify(error));    		
    	});
    	 
      },
      
      // 후원동물 수정
      update:function(){
    	  
    	  let id = $("#id").val();
 
    	  let data={
    			  type:$("#type").val(),
    			  title:$("#title").val(),
    			  name:$("#name").val(),
    			  profileImage:$("#profileImage").val(),
    			  content:$("#content").val()
    	  };
    	  
    	  console.log(data); 
    	  $.ajax({
    		type:"PUT",
    		url:"/api/Support/"+id,
    		data:JSON.stringify(data),
    		contentType:"application/json;charset=utf-8",
    		dataType:"json"
    	  }).done(function(resp){
    		  alert("수정되었습니다.");
    		  location.href="/SupportList";
    	  }).fail(function(error){
    		 alert(JSON.stringify(error));
    	  });
    		 
    	 
      },   
      // 후원동물 삭제
      findById:function(){
    	  
    	  let id = $("#id").val();
    	  
    	  
    	 if(confirm("정말 삭제하시겠습니까?")){
    		 $.ajax({
    			 type:"DELETE",
    			 url:"/api/Support/"+id,
    			 dataType:"json",
    		 }).done(function(resp){
    			 alert("삭제가 완료 되었습니다.")
    			  location.href="/SupportList";
    		 }).fail(function(error){
    			 alert(JSON.stringify(error));    	
    		 });
    	    }else{
    	    	alert("삭제가 취소 되었습니다. ")
    	    }
    	 },
    
}
index.init();
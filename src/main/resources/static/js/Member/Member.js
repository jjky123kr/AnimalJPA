let index={
		init:function(){
			$("#btn-save").on("click",()=>{ // function(){},()=>{} this 를 바인딩하기
											// 위해서
				this.save();
			}); // btn-save 클릭시 아래가 실행 된다.
			
			 $("#btn-check-duplicate").on("click", function() {
			      index.checkDuplicate();
		    });
			
//			
// $("#btn-login").on("click",()=>{ // function(){},()=>{} this 를 바인딩하기 위해서
// this.login();
// }); // btn-save 클릭시 아래가 실행 된다.
			
		},
		
    save:function(){
    	// alert("user의 save함수 호출됨");
    	let data={
    		username:$("#username").val(),
    		   name:$("#name").val(),
 		       password:$("#password").val(),
 		       jumin1:$("#jumin1").val(),
 		       jumin2:$("#jumin2").val(),
 		       email:$("#email").val(),
 		       phon1:$("#phon1").val(),
 		       phon2:$("#phon2").val(),
 		       phon3:$("#phon3").val(),
 		       post:$("#post").val(),
 		       address1:$("#address1").val(),
 		       address2:$("#address2").val() 
    	};
    	
    	// console.log(data);
    	// 회원가입
    	// ajax 호출시 default 가 비동기 호출
    	// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청한다.
    	// ajax 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트 변환 가능하다.
    	$.ajax({
    		 type:"POST",
    		 url:"/auth/joinProc",// 컨트롤 주소 값
    		 data:JSON.stringify(data), // http body 데이터
    		 contentType:"application/json;charset=utf-8", // body 데이터가 어떤
															// 타입인지(MIME)
    		 dataType:"json"// 요청을 서버로 해서 응답이 왔을때 기본으로 모든적이 문자열(생긴것이
							// json)이라며=>javascript 오브젝트로변경
    		// .을 통해서 사용 => 회원가입 수행 요청
    	}).done(function(resp) {
    	    if (resp.status === 200) {
    	        alert("회원가입이 완료되었습니다.");
    	        location.href = "/auth/LoginForm";
    	    } else if (resp.status === 400) {
    	        alert("회원가입에 실패했습니다. 에러 메시지: " + resp.message);
    	    }
    	}).fail(function(error) {
    	    alert(JSON.stringify(error));
    	});
    },
    
    checkDuplicate: function() {
    	var username = $("#username").val();
    	
    	$.ajax({
    		  type: "POST",
    		  url: "/auth/checkDuplicate",
    		  data: JSON.stringify({ username: username }),
    		  contentType: "application/json;charset=utf-8",
    		  dataType: "json"
    		})
    		.done(function(resp) {
    		  if (resp.status === 200) {
    		    // 중복되지 않은 경우
    		    location.href = "/auth/JoinForm";
    		    alert("사용 가능한 아이디입니다.");

    		    setTimeout(function() {
    		      $("#username").focus();
    		    }, 100);
    		  } else if (resp.status === 400) {
    		    // 중복된 경우
    		    location.href = "/auth/JoinForm";
    		    alert("중복된 아이디입니다. 다른 아이디를 입력해주세요.");
    		  }
    		})
    		.fail(function(error) {
    		  alert(JSON.stringify(error));
    		});

    	},
}
index.init();
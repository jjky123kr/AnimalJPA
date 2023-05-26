function check(){
	var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
	var getID=RegExp(/^[a-zA-Z0-9]{4,12}$/);
	var getName=RegExp(/^[가-힣]+$/);
	var fmt = RegExp(/^\d{6}[1234]\d{6}$/);
	var Passwd=RegExp( /(?=.*[a-zA-ZS])(?=.*?[#?!@$%^&*-]).{6,24}/);
    var Jumin=new Array(13);
    
    // 아이디 공백확인
    if($("#username").val()==""){
    	alert("아이디를 입력하세요");
    	$("#username").focus();
    	return false;
    }
    // 아이디 유효성 검사
   if(!getID.test($("#username").val())){
	   alert("아이디는 4~12자, 영문 대소문자, 숫자만 가능합니다.");
	   $("#username").val("");
	   $("#username").focus();
	   return false;
   }
   // 이름 공백 확인
   if($("#name").val()==""){
	   alert("이름을 입력하세요");
	   $("#name").focus();
	   return false;
   }
   // 이름 유효성 검사
  if(!getName.test($("#name").val())){
	  alert("이름은 한글만 입력하세요")
	  $("#name").val("");
	  $("#name").focus();
	  return false;
  }
  // 비밀번호 공백 확인
  if($("#password").val()==""){
	  alert("비밀번호를 입력하세요");
	  $("#password").focus();
	  return false;
  }
  if($("#pwcheck").val()==""){
	  alert("비밀번호를 입력하세요");
	  $("#pwcheck").focus();
	  return false;
  }
  
  // 비밀번호 와 아이디 같음 확인
  if($("#username").val()==$("#password").val()){
	  alert("아이디와 비밀번호가 같습니다.");
	  $("#password").val("");
	  $("#password").focus();
	  return false;
  }
  // 비밀번호 유효성 검사 
  if(!Passwd.test($("#password").val())){
	  alert("비밀번호는 4~12자 영문 대소문자, 특수문자 혼합 사용해야 합니다.");
	  $("#password").val("");
	  $("#password").focus();
	  return false;
	  
  }
  // 비밀번호 확인 
  if($("#password").val() !=$("#pwcheck").val()){
	  alert("비밀번호가 다릅니다. 다시 입력해주세요");
	  $("#pwcheck").val("");
	  $("#pwcheck").focus();
	  return false;
  }
 if($("#email").val()==""){
	 alert("이메일 작성하세요");
	 $("#email").val("");
	 $("#email").focus();
	 return false;
 }
 
 //이메일 유효성 검사
 if(!getMail.test($("#email").val())){
   alert("이메일형식에 맞게 입력해주세요")
   $("#email").val("");
   $("#email").focus();
   return false;
 }
 
    //주민번호 공백 확인
      if(($("#jumin1").val() == "") || ($("#jumin2").val() == "")){
        alert("주민등록번호를 입력해주세요");
        $("#jumin1").focus();
        return false;
      }
     if(check_jumin() ==false){
        return false;
      }
  
     // 주민번호 유효성 
   function check_jumin() {
    var jumins3 = $("#jumin1").val() + $("#jumin2").val();
      //주민등록번호 생년월일 전달
          
      var fmt = RegExp(/^\d{6}[1234]\d{6}$/)  //포멧 설정
      var buf = new Array(13);
 
      //주민번호 유효성 검사
      if (!fmt.test(jumins3)) {
        alert("주민등록번호 형식에 맞게 입력해주세요");
        $("#jumin1").focus();
        return false;
      }
      //주민번호 존재 검사
      for (var i = 0; i < buf.length; i++){
        buf[i] = parseInt(jumins3.charAt(i));
      }
 
      var multipliers = [2,3,4,5,6,7,8,9,2,3,4,5];// 밑에 더해주는 12자리 숫자들 
      var sum = 0;
 
      for (var i = 0; i < 12; i++){
      sum += (buf[i] *= multipliers[i]);// 배열끼리12번 돌면서 
    }
 
    if ((11 - (sum % 11)) % 10 != buf[12]) {
      alert("잘못된 주민등록번호 입니다.");
      $("#jumin1").val("");
      $("#jumin1").focus();
      return false;
    }
 
   }
}
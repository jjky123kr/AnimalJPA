# AnimalJPA
# Rest API를 이용한 게시판 응용 개인 프로젝트 

### DB 설계  

# 주요 기능
## 카카오 로그인(소셜 로그인)

1.카카오 APi 사용하여 로그인 기능을 설계 한다. 
1) 로그인 페이지에 버튼을 추가한다. 

<img width="637" alt="카카오" src="https://github.com/jjky123kr/AnimalJPA/assets/107549149/a8fc6bf3-aca4-4dc9-a86a-7ead8da89f61">

2) 카카오 로그인 동의 화면

<img width="391" alt="카카오 2" src="https://github.com/jjky123kr/AnimalJPA/assets/107549149/020e370f-e855-4be4-923e-0ca5ebee6fcd">

3) 카카오 로그인 을 위해서 토큰을 받는 방법 

3-1) HttpHeader 오브젝트 생성  
3-2) HttpBody 오브젝트 생성  
3-3) HttpHeader 와 HttpBody 담는다. 
3-4) Http 로 요청 할때 POST 방식 response 변수 응답을 받는다. 

```java
RestTemplate rt = new RestTemplate(); 이용한다.

// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//HttpBody 오브젝트 생성 
		MultiValueMap<String, String>params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "client_id값");
		params.add("redirect_uri","redirect_uri 주소");
		params.add("code", code);
		  
		//HttpHeader 와 HttpBody를 하나의 오브젝트에 담기
    // 이때HttpEntity<MultiValueMap<String, String>> 사용한다. 
		HttpEntity<MultiValueMap<String, String>>kakaoTokenRequest=
		new HttpEntity<>(params,headers);	
		// Http요청하기 - post방식으로 - 그리고 response 변수의 응답 받는다.
		ResponseEntity<String>response=rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
				);
		//Gson,Json Simple, ObjectMapper
		ObjectMapper ObjectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		
		try {
			oauthToken = ObjectMapper.readValue(response.getBody(),OAuthToken.class );
		} catch (JsonMappingException e) {
		
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("카카오토큰:"+oauthToken.getAccess_token());
  ```

5) 토큰으로 사용자 정보를 받는다.

```java
RestTemplate rt2 = new RestTemplate();
		// HttpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		headers2.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		
		  
		//HttpHeader 와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = 
				new HttpEntity<>(headers2);
		// Http요청하기 - post방식으로 - 그리고 response 변수의 응답 받는다.
		ResponseEntity<String>response2=rt2.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest2,
				String.class
				);
		
		System.out.println(response2.getBody());
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile=null;
		
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		// User 오브젝트 : username, password, email
				System.out.println("카카오 아이디(번호) : "+kakaoProfile.getId());
				System.out.println("카카오 이메일 : "+kakaoProfile.getKakao_account().getEmail());
				
				System.out.println("블로그서버 유저네임 : "+kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
				System.out.println("블로그서버 이메일 : "+kakaoProfile.getKakao_account().getEmail());
				// UUID란 -> 중복되지 않는 어떤 특정 값을 만들어내는 알고리즘
				System.out.println("블로그서버 패스워드 : "+cosKey);
```
6) 사용자 정보를 member 테이블에 저장하여, 회원가입을 시킨다. 

```java
				Member kakaoUser = Member.builder()
						.username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
						.password(cosKey)
						.email(kakaoProfile.getKakao_account().getEmail())
						.oauth("kakao")
						.build();
 ```
7)  로그인 할 경우 기존에 있던 회원가입 이 안되 있을 경우에는 회원가입 기능 
  
```java
Member originUser = memberService.회원찾기(kakaoUser.getUsername());

				if(originUser.getUsername() == null) {
					System.out.println("기존 회원이 아니기에 자동 회원가입을 진행합니다");
					memberService.회원가입(kakaoUser);
				}
```
## summernote 에디터 사용 (게시판)

## Spring Security 
### 권한 설정 과 회원가입 해야 사용가능 

```java
	@Bean
	DefaultSecurityFilterChain confiaure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()  // csrf 토큰 비활성화 (테스트시 걸어두는 게 좋음)
		.authorizeRequests()
		.antMatchers("/", "/auth/**","/main/**",  "/mainAdmin/**" ,"/js/**", "/css/**", "/images/**", "/dummy/**","/font/**") 
		.permitAll()
		.antMatchers("/user/**").hasRole("USER")
		.antMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest()
		.authenticated()
```
### 회원 가입 
1. .loginProcessingUrl("/auth/loginProc") // 스프링시큐리티가 해당 주소로 요청오는 로그인을 가로챈다.

```java
		.and()
	      .formLogin()
	      .loginPage("/auth/LoginForm")
	      .loginProcessingUrl("/auth/loginProc") // 스프링시큐리티가 해당 주소로 요청오는 로그인을 가로챈다.
          .defaultSuccessUrl("/main"); // 대신 로그인	
	       return http.build();
	}
```
### 회원가입 시 비밀번호 해쉬와

```java
@Bean
	BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}

	// 시큐리티가 대신 로그인 해주는데 password를 가로채기를 하는데
	// 해당password가 뭘로 해수가 되어 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교 할 수 있다.

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(PrincipalDetailService).passwordEncoder(encodePWD());
	}
 ```

## rest 방식으로 유효성 검사

### js
* 글 등록 할 경우 alert 에러 메세지를 출력한다. 
```java
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
```
### Controller 페이지 
```java
// 입양 등록	
	@PostMapping("/api/Adopt")
	public ResponseDto<Integer> save(@Valid @RequestBody Adopt adopt, BindingResult result, @AuthenticationPrincipal PrincipalDetail principal) {
	    if (result.hasErrors()) {
	        // 유효성 검사 실패 처리
	        String errorMessage = result.getFieldErrors()
	                .stream()
	                .map(DefaultMessageSourceResolvable::getDefaultMessage)
	                .findFirst()
	                .orElse("유효성 검사 실패");

	        return new ResponseDto<>(400, null, errorMessage);
	    }

	    System.out.println("save: 호출");
	    adoptService.save(adopt, principal.getMember());
	    return new ResponseDto<>(200, 1, null);
	}
```


## 게시판 검색 기능 


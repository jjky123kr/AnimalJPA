# AnimalJPA
# Rest API를 이용한 게시판 응용 개인 프로젝트 

# 주요 기능
## 카카오 로그인(소셜 로그인)

1.카카오 APi 사용하여 로그인 기능을 설계 한다. 
1) 로그인 페이지에 버튼을 추가한다. 

<img width="637" alt="카카오" src="https://github.com/jjky123kr/AnimalJPA/assets/107549149/a8fc6bf3-aca4-4dc9-a86a-7ead8da89f61">

2) 카카오 로그인 동의 화면

<img width="391" alt="카카오 2" src="https://github.com/jjky123kr/AnimalJPA/assets/107549149/020e370f-e855-4be4-923e-0ca5ebee6fcd">

3) 자동 로그인 진행 


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
 .loginPage("/auth/LoginForm"
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
* @Valid 사용 과, BindingResult result 결과를 보여준다. 
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

*  Page<Adopt>findByTitleContaining(String keyword, Pageable pageable);
* keyword 사용한다. 
	
```java
// 입양검색목록 페이지
	@Transactional(readOnly = true)
	public Page<Adopt> 입양목록(Pageable pageable, String keyword) {
		System.out.println("service 에 들어옴");
		return adoptRepository.findByTitleContaining(keyword,pageable);
	}
```



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

<img width="1133" alt="a" src="https://github.com/jjky123kr/AnimalJPA/assets/107549149/d3b60096-6b86-485c-9d6a-5be6b0f19067">

### Summernote는 이미지를 저장하는 방식 바이러리 이다. 
### 바이러리의 단점 : 용량이 너무 많이 차지한다.
### 그래서 이미지를 업로드 할때 DB에 따로 저장하는 코드를 추가한다. 


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

<img width="874" alt="로그인" src="https://github.com/jjky123kr/AnimalJPA/assets/107549149/33746539-a010-4971-a71f-516888f52d3a">


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

<img width="865" alt="검색" src="https://github.com/jjky123kr/AnimalJPA/assets/107549149/58f4fac7-f023-4535-a782-26126ef0821b">
	
결과 페이지	
	
![image](https://github.com/jjky123kr/AnimalJPA/assets/107549149/4452e9d5-1ae1-4edf-90ec-74ea743c2e81)

	


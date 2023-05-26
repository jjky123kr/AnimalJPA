package com.Animal.blog.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import com.Animal.blog.Config.auth.PrincipalDetailService;

import ch.qos.logback.core.pattern.color.BoldCyanCompositeConverter;

@Configuration // loc
public class SecurityConfig {

	@Autowired
	private PrincipalDetailService PrincipalDetailService; // 주입

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
		.and()
	      .formLogin()
	      .loginPage("/auth/LoginForm")
	      .loginProcessingUrl("/auth/loginProc") // 스프링시큐리티가 해당 주소로 요청오는 로그인을 가로챈다.
          .defaultSuccessUrl("/main") ;  // 대신 로그인	      
	      return http.build();
	}
}

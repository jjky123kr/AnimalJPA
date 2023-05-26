package com.Animal.blog.Conetroller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Animal.blog.Config.auth.PrincipalDetail;

// 인증이 안된 사용자들이 출립할 수 있는 경로를 /auth/**허용
// 그냥 주소가 / 이면 index.jsp 허용
// static 이하에 있는 /js/**,/css/**,/image/**

@Controller
public class UserController {

	// cover 페이지
	@GetMapping("/")
	public String index() {
		return "index";
	}
    
	// user main 페이지	
	@GetMapping("/main")
	public String main() {  
		return "main";
	}

	// 회원가입 컨트롤
	@GetMapping("/auth/JoinForm")
	public String joinForm() {
		return "Member/JoinForm";
	}

	// 로그인 폼
	@GetMapping("/auth/LoginForm")
	public String loginForm() {
		return "Member/LoginForm";
	}
	

}

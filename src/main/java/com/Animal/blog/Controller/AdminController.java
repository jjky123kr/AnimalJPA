package com.Animal.blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Animal.blog.Model.Member;
import com.Animal.blog.Service.MemberService;

@Controller
public class AdminController {

	@Autowired
	private MemberService memberService;
	
	// 관리자 메인 화면
	@GetMapping("/admin")
	public String admin() {
		return"Admin/AdminMain";
	}
	
	
	// 회원조회 
	@GetMapping("/admin/Member")
	public String memberList(Model model) {
		model.addAttribute("members", memberService.회원목록());	
		System.out.println("컨트롤 확인 테스트");
		return"Admin/MemberList";
	}
				
	// 회원 문의 글
}

package com.Animal.blog.Conetroller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Animal.blog.DTO.ResponseDto;
import com.Animal.blog.Model.Member;
import com.Animal.blog.Model.RoleType;
import com.Animal.blog.Service.MemberService;

@RestController
public class MemberApiController {

	@Autowired

	private MemberService memberService;

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer>save( @RequestBody Member member){
		   member.setRole(RoleType.USER);
		   memberService.회원가입(member);
		   return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
}

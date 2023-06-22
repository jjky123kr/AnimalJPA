package com.Animal.blog.Controller.api;


import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
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
	public ResponseDto<Integer>save(@RequestBody Member member){
		    member.setRole(RoleType.USER);
		    memberService.회원가입(member);
		    return new ResponseDto<>(200, 1, null);
	}
	
	// 회원가입 시 중복 검사 
	@PostMapping("/auth/checkDuplicate")
	public ResponseDto<?>check(@RequestBody Map<String , String>requestData){
		 String username = requestData.get("username");
	        boolean isDuplicate = memberService.isDuplicatedUsername(username);

	        if (isDuplicate) {
	            String errorMessage = "중복된 아이디입니다.";
	            return new ResponseDto<>(400, null, errorMessage);
	        } else {
	            return new ResponseDto<>(200, null, "사용 가능한 아이디입니다.");
	        }
	    }
	
	}
	


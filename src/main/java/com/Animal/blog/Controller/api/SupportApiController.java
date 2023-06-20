package com.Animal.blog.Controller.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Animal.blog.DTO.ResponseDto;
import com.Animal.blog.Model.Support;
import com.Animal.blog.Service.SupportService;

@RestController
public class SupportApiController {

	@Autowired
	private SupportService supportService;
	
	//후원동물 등록
	@PostMapping("/api/Support")
	public ResponseDto<Integer>save(@Valid @RequestBody Support support , BindingResult result){
		System.out.println("후원동물등록 컨트롤");
		if (result.hasErrors()) {
	        // 유효성 검사 실패 처리
	        String errorMessage = result.getFieldErrors()
	                .stream()
	                .map(DefaultMessageSourceResolvable::getDefaultMessage)
	                .findFirst()
	                .orElse("유효성 검사 실패");

	        return new ResponseDto<>(400, null, errorMessage);
	    }
		supportService.등록(support);
		 return new ResponseDto<>(200, 1, null);
		
	}
	// 후원동물 수정
	@PutMapping("/api/Support/{id}")
	public ResponseDto<Integer>update(@Valid @RequestBody Support support,@PathVariable int id , BindingResult result) {
		System.out.println("후원동물 수정 컨트롤");
		if (result.hasErrors()) {
	        // 유효성 검사 실패 처리
	        String errorMessage = result.getFieldErrors()
	                .stream()
	                .map(DefaultMessageSourceResolvable::getDefaultMessage)
	                .findFirst()
	                .orElse("유효성 검사 실패");

	        return new ResponseDto<>(400, null, errorMessage);
	    }
		supportService.후원수정(support,id);
		return new ResponseDto<>(200, 1, null);
	}
	
	// 후원동물 삭제 
	@DeleteMapping("/api/Support/{id}")
	public ResponseDto<Integer>delete(@PathVariable int id){
		
		Support support = supportService.findById(id);
		
		supportService.후원삭제(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1,null);
		
	}
	
}

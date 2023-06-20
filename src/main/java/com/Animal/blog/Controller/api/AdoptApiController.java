package com.Animal.blog.Controller.api;

import java.io.File;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Animal.blog.Config.auth.PrincipalDetail;
import com.Animal.blog.DTO.AdoptRequestDto;
import com.Animal.blog.DTO.ResponseDto;
import com.Animal.blog.DTO.UploadResponseDto;
import com.Animal.blog.Model.Adopt;
import com.Animal.blog.Model.Animal;
import com.Animal.blog.Model.UploadFile;
import com.Animal.blog.Service.AdoptService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController

public class AdoptApiController {

	@Autowired
	private AdoptService adoptService;

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
	
	// 입양 수정 
	@PutMapping("/api/Adopt/{id}")
	public ResponseDto<Integer>update(@Valid @RequestBody Adopt adopt, BindingResult result,  @PathVariable int id){
		 if (result.hasErrors()) {
		        // 유효성 검사 실패 처리
		        String errorMessage = result.getFieldErrors()
		                .stream()
		                .map(DefaultMessageSourceResolvable::getDefaultMessage)
		                .findFirst()
		                .orElse("유효성 검사 실패");

		        return new ResponseDto<>(400, null, errorMessage);
		    }

		System.out.println("update:호출");
		System.out.println("update id:"+id);
		System.out.println("update age:"+adopt.getAge());
		adoptService.update(adopt,id);
		 return new ResponseDto<>(200, 1, null);
	}
	
	
	
	// 입양 삭제 (입양 완료 후 삭제)
	@DeleteMapping("/api/Adopt/{id}")
	public ResponseDto<Integer> deleteByid(@PathVariable int id) {
		
		Adopt adopt = adoptService.findById(id);
	
		adoptService.글삭제(id);
		 return new ResponseDto<Integer>(HttpStatus.OK.value(), 1, null);

	}
	
}

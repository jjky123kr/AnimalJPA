package com.Animal.blog.Controller.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Animal.blog.Config.auth.PrincipalDetail;
import com.Animal.blog.DTO.ResponseDto;
import com.Animal.blog.Model.Animal;
import com.Animal.blog.Service.AnimalService;

@RestController
public class AnimalApiController {

	@Autowired
	private AnimalService animalService;
	
	// 반려동물 등록
	@PostMapping("/api/Animal")
	public ResponseDto<Integer>save(@Valid @RequestBody Animal animal,@AuthenticationPrincipal PrincipalDetail principal , BindingResult result ){
		System.out.println("save:호출");
		 if (result.hasErrors()) {
		        // 유효성 검사 실패 처리
		        String errorMessage = result.getFieldErrors()
		                .stream()
		                .map(DefaultMessageSourceResolvable::getDefaultMessage)
		                .findFirst()
		                .orElse("유효성 검사 실패");

		        return new ResponseDto<>(400, null, errorMessage);
		    }
		animalService.save(animal,principal.getMember());
		  return new ResponseDto<>(200, 1, null);
		
	}
	// 동물 정보 삭제 
	@DeleteMapping("/api/Animal/{id}")
	public ResponseDto<Integer>deleteByid(@PathVariable int id){
		System.out.println("delete:호출");
		animalService.delete(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1 ,null);
	}
	// 동물 정보 수정
	@PutMapping("/api/Animal/{id}")
	public ResponseDto<Integer>update(@Valid @PathVariable int id ,@RequestBody Animal animal , BindingResult result){
		System.out.println("수정 호출");
		 if (result.hasErrors()) {
		        // 유효성 검사 실패 처리
		        String errorMessage = result.getFieldErrors()
		                .stream()
		                .map(DefaultMessageSourceResolvable::getDefaultMessage)
		                .findFirst()
		                .orElse("유효성 검사 실패");

		        return new ResponseDto<>(400, null, errorMessage);
		    }
		animalService.update( id, animal);
		 return new ResponseDto<>(200, 1, null);
	}
	
}

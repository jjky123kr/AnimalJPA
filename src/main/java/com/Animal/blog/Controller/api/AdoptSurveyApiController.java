package com.Animal.blog.Controller.api;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Animal.blog.DTO.ResponseDto;
import com.Animal.blog.Model.Adopt;
import com.Animal.blog.Model.AdoptSurvey;
import com.Animal.blog.Service.AdoptSurveyService;

@RestController
public class AdoptSurveyApiController {

	@Autowired
	private AdoptSurveyService adoptSurveyService;
	
	// 입양신청 등록
	@PostMapping("/api/AdoptSurvey/{adoptId}")
	public ResponseDto<Integer>save(@Valid @PathVariable int adoptId,@RequestBody AdoptSurvey adoptSurvey , BindingResult result){
		System.out.println("설문조사 Controller 호출"); 
		 if (result.hasErrors()) {
		        // 유효성 검사 실패 처리
		        String errorMessage = result.getFieldErrors()
		                .stream()
		                .map(DefaultMessageSourceResolvable::getDefaultMessage)
		                .findFirst()
		                .orElse("유효성 검사 실패");

		        return new ResponseDto<>(400, null, errorMessage);
		    }
		adoptSurveyService.설문조사(adoptSurvey , adoptId);
		 return new ResponseDto<>(200, 1, null);
		
	}
  // 입양 신청 삭제 
	@DeleteMapping("/api/AdoptSurvey/{id}")
	public ResponseDto<Integer>deleteByid( @PathVariable int id  ){
		
		
		
		AdoptSurvey adoptSurvey = adoptSurveyService.findById(id);
		
		adoptSurveyService.글삭제(id);
		 return new ResponseDto<Integer>(HttpStatus.OK.value(), 1, null);
	}
	// 입양 승인 처리	
	@PostMapping("/completeAdoption")
	public ResponseDto<Integer>approve(@RequestBody AdoptSurvey adoptSurvey ){
		adoptSurveyService.completeAdoption(adoptSurvey.getId());
		 return new ResponseDto<Integer>(HttpStatus.OK.value(), 1, null);
	}
}

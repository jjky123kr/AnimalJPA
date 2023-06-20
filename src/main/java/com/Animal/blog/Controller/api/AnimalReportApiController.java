package com.Animal.blog.Controller.api;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Animal.blog.Config.auth.PrincipalDetail;
import com.Animal.blog.DTO.ResponseDto;
import com.Animal.blog.Model.Adopt;
import com.Animal.blog.Model.AnimalReport;
import com.Animal.blog.Service.AnimalReportService;

@RestController
public class AnimalReportApiController {

	@Autowired
	private AnimalReportService  animalReportService;
	
	@PostMapping("/api/Report")
	public ResponseDto<Integer>save(@Valid @RequestBody AnimalReport animalReport,BindingResult result, @AuthenticationPrincipal PrincipalDetail principal){
		 if (result.hasErrors()) {
		        // 유효성 검사 실패 처리
		        String errorMessage = result.getFieldErrors()
		                .stream()
		                .map(DefaultMessageSourceResolvable::getDefaultMessage)
		                .findFirst()
		                .orElse("유효성 검사 실패");

		        return new ResponseDto<>(400, null, errorMessage);
		    }
		System.out.println("제보 컨트롤");
		animalReportService.save(animalReport,principal.getMember());
		  return new ResponseDto<>(200, 1, null);
	}
	
	// 수정
	
	@PutMapping("/api/Report/{id}")
	public ResponseDto<Integer>update(@Valid @RequestBody AnimalReport animalReport,@PathVariable int id ,BindingResult result){
		if (result.hasErrors()) {
	        // 유효성 검사 실패 처리
	        String errorMessage = result.getFieldErrors()
	                .stream()
	                .map(DefaultMessageSourceResolvable::getDefaultMessage)
	                .findFirst()
	                .orElse("유효성 검사 실패");

	        return new ResponseDto<>(400, null, errorMessage);
	    }
		System.out.println("제보수정 컨트롤");
		animalReportService.update(animalReport,id);
		 return new ResponseDto<>(200, 1, null);
	}
	
	// 삭제 컨트롤
	// 입양 삭제 (입양 완료 후 삭제)
		@DeleteMapping("/api/Report/{id}")
		public ResponseDto<Integer> deleteByid(@PathVariable int id) {
			
			AnimalReport animalReport = animalReportService.findById(id);
		
			animalReportService.글삭제(id);
			return new ResponseDto<Integer>(HttpStatus.OK.value(), 1, null);
		}
}

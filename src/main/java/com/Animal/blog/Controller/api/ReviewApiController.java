package com.Animal.blog.Controller.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.Animal.blog.Model.AdoptReview;
import com.Animal.blog.Service.AdoptReviewService;

@RestController
public class ReviewApiController {

	@Autowired
	private AdoptReviewService adoptReviewService;
	
	//입양후기 등록
	@PostMapping("/api/Review")
	public ResponseDto<Integer>save(@Valid @RequestBody AdoptReview adoptReview , @AuthenticationPrincipal PrincipalDetail principal, BindingResult result ){
		System.out.println("리뷰 컨트롤");
		 if (result.hasErrors()) {
		        // 유효성 검사 실패 처리
		        String errorMessage = result.getFieldErrors()
		                .stream()
		                .map(DefaultMessageSourceResolvable::getDefaultMessage)
		                .findFirst()
		                .orElse("유효성 검사 실패");

		        return new ResponseDto<>(400, null, errorMessage);
		    }
		adoptReviewService.save(adoptReview,principal.getMember());
		 return new ResponseDto<>(200, 1, null);
		
	}
	// 입양후기 수정
	
	@PutMapping("/api/Review/{id}")
     public ResponseDto<Integer>update(@Valid @RequestBody AdoptReview adoptReview,@PathVariable int id , BindingResult result){
		System.out.println("update:호출");
		System.out.println("리뷰 수정 컨트롤");
		 if (result.hasErrors()) {
		        // 유효성 검사 실패 처리
		        String errorMessage = result.getFieldErrors()
		                .stream()
		                .map(DefaultMessageSourceResolvable::getDefaultMessage)
		                .findFirst()
		                .orElse("유효성 검사 실패");

		        return new ResponseDto<>(400, null, errorMessage);
		    }
		adoptReviewService.리뷰수정(id,adoptReview);
		return new ResponseDto<>(200, 1, null);
	}
	
	// 입양후기 삭제 
	@DeleteMapping("/api/Review/{id}")
	public ResponseDto<Integer> deleteByid(@PathVariable int id){
		AdoptReview adoptReview = adoptReviewService.findById(id);
		
		adoptReviewService.글삭제(id);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1,null);
	}
	
}

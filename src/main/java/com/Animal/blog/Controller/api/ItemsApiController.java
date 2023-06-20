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
import com.Animal.blog.Model.DonationItems;
import com.Animal.blog.Service.ItemsService;

@RestController
public class ItemsApiController {

	
	@Autowired
	private ItemsService itemsService;
	
	// 물품 등록
	
	@PostMapping("/api/Items")
	public ResponseDto<Integer>save(@Valid @RequestBody DonationItems doationItmes, @AuthenticationPrincipal PrincipalDetail principal,BindingResult result) {
		 if (result.hasErrors()) {
		        // 유효성 검사 실패 처리
		        String errorMessage = result.getFieldErrors()
		                .stream()
		                .map(DefaultMessageSourceResolvable::getDefaultMessage)
		                .findFirst()
		                .orElse("유효성 검사 실패");

		        return new ResponseDto<>(400, null, errorMessage);
		    }
		
		
		DonationItems donationItmes = new DonationItems();
		
		if ("notion".equals(donationItmes.getNoticeOption())) {
		    donationItmes.setIsPinned(true);
		} else {
		    donationItmes.setIsPinned(false);
		}
		
		itemsService.save(doationItmes,principal.getMember());
		 return new ResponseDto<>(200, 1, null);
	}
	
	// 물품 수정
	@PutMapping("/api/Itmes/{id}")
	public ResponseDto<Integer>update(@Valid @RequestBody DonationItems donationItems ,@PathVariable int id,BindingResult result ){
		
		 if (result.hasErrors()) {
		        // 유효성 검사 실패 처리
		        String errorMessage = result.getFieldErrors()
		                .stream()
		                .map(DefaultMessageSourceResolvable::getDefaultMessage)
		                .findFirst()
		                .orElse("유효성 검사 실패");

		        return new ResponseDto<>(400, null, errorMessage);
		    }
		 System.out.println("물품 수정 컨트롤");
		itemsService.update(donationItems,id);
		return new ResponseDto<>(200, 1, null);
	}
	
	@DeleteMapping("/api/Items/{id}")
	public ResponseDto<Integer>delete(@PathVariable int id){
		DonationItems donationItems = itemsService.findById(id);
		
		itemsService.글삭제(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1,null);
	}
	
	
	// 물품 삭제
	
	
	
}

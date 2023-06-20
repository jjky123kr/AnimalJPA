package com.Animal.blog.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Animal.blog.Model.Adopt;
import com.Animal.blog.Service.AdoptService;
import com.Animal.blog.Service.AdoptSurveyService;


@Controller
public class AdoptController {

	@Autowired
	private AdoptService adoptService;
	
	@Autowired
	private AdoptSurveyService adoptSurveyService;
	
	//입양 동물 절차 등록 폼
	   @GetMapping("/AdoptForm")
	   public String adopt() {
		   return"Adopt/AdoptForm";
	   }
	   
	// 입양동물 목록 페이지
	@GetMapping("/AdoptList")
	public String adoptList( String keyword, Model model, @PageableDefault(size=10,sort="id",direction = Sort.Direction.DESC)Pageable pageable) {
		Page<Adopt> adopt = null;
		
		if(keyword == null) {
			adopt = adoptService.입양목록(pageable); // 기존 리스트 페이지
		}else {
			adopt = adoptService.입양목록(pageable, keyword);
		}
		
		model.addAttribute("adopt", adopt);
	
		System.out.println("컨트롤 확인");
		return"Adopt/AdoptList";
	}
	
	// 입양동물 상세 페이지 
	@GetMapping("/adopt/{id}")
	public String findById(@PathVariable int id , Model model) {
		model.addAttribute("adopt", adoptService.입양상세(id));
		System.out.println("입양 상세 페이지");
		return"Adopt/AdoptDetail";
	}
	
	// 입양동물 수정 페이지
		@GetMapping("/adopt/{id}/updateForm")
		public String updateForm(@PathVariable int id , Model model) {
			model.addAttribute("adopt", adoptService.입양상세(id));
			System.out.println("입양수정 컨트롤");
			return"Adopt/AdoptUpdateForm";
		}
	
	// 입양동물 신청 페이지
	@GetMapping("/adopt/{id}/SurveyForm")
	public String content(@PathVariable int id , Model modle) {
		modle.addAttribute("adopt",adoptService.입양상세(id));
		return"Adopt/AdoptSurveyForm";
	}
	
	// 입양동물 신청 목록
	@GetMapping("/AdoptSurveyList")
	public String list(Model model,@PageableDefault(size=10,sort="id",direction = Sort.Direction.DESC)Pageable pageable){
		model.addAttribute("survey",adoptSurveyService.입양신청목록(pageable));
		return "Adopt/AdoptSurveyList";
	}

    // 입양신청 상세페이지
	@GetMapping("/survey/{id}")
	public String findSurvey(@PathVariable int id , Model model) {
		model.addAttribute("survey", adoptSurveyService.입양신청상세(id));
		return"Adopt/AdoptSurveyDetail";
	}
	
	

}

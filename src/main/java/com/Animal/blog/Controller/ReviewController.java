package com.Animal.blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Animal.blog.Model.AdoptReview;
import com.Animal.blog.Service.AdoptReviewService;

@Controller
public class ReviewController {

	@Autowired
	private AdoptReviewService adoptReviewService;
	
	// 입양후기 
	@GetMapping("/Admin/AdoptReview")
	public String adoptReviewList(String keyword,Model model,@PageableDefault(size=10,sort="id",direction = Sort.Direction.DESC)Pageable pageable) {
		Page<AdoptReview> review = null;
		
		if(keyword == null) {
			review = adoptReviewService.리뷰목록(pageable); // 기존 리스트 페이지
		}else {
			review = adoptReviewService.리뷰목록(pageable, keyword);
		}
		System.out.println("입양후기 컨트롤");
		model.addAttribute("review", review);
		
		return"Review/AdoptReviewList";
	}
	
	// 후기 상세 페이지 
	@GetMapping("/Admin/Review/{id}")
	public String ReviewDetail(Model model , @PathVariable int id) {
		model.addAttribute("review", adoptReviewService.후기상세(id));
		System.out.println("후원상세 컨트롤");
		return"Review/ReviewtDetail";
	}
}

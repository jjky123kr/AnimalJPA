package com.Animal.blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.Animal.blog.Config.auth.PrincipalDetail;
import com.Animal.blog.Model.Adopt;
import com.Animal.blog.Model.AdoptReview;
import com.Animal.blog.Service.AdoptReviewService;
import com.Animal.blog.Service.AdoptService;
import com.Animal.blog.Service.AnimalReportService;
import com.Animal.blog.Service.AnimalService;
import com.Animal.blog.Service.SupportService;

// 인증이 안된 사용자들이 출립할 수 있는 경로를 /auth/**허용
// 그냥 주소가 / 이면 index.jsp 허용
// static 이하에 있는 /js/**,/css/**,/image/**

@Controller
public class MemberController {

	@Autowired
	private AnimalService animalService;
	
	@Autowired
	private AdoptService adoptService;
	
	@Autowired
	private SupportService supportService;
	
	@Autowired
	private AdoptReviewService adoptReviewService;
	
	@Autowired
	private AnimalReportService animalReportService;
	
	// cover 페이지
	@GetMapping("/")
	public String index() {
		return "index";
	}
    
	// user main 페이지	
	@GetMapping("/main")
		public String index(Model model,@PageableDefault(size=10,sort="animalid",direction = Sort.Direction.DESC)Pageable pageable) { 	
			model.addAttribute("animals",animalService.글목록(pageable)); 
			System.out.println("컨트롤 확인 테스트 입니다.");
		return "Member/main";
	}

	// 회원가입 컨트롤
	@GetMapping("/auth/JoinForm")
	public String joinForm() {
		return "Member/JoinForm";
	}

	// 로그인 폼
	@GetMapping("/auth/LoginForm")
	public String loginForm() {
		return "Member/LoginForm";
	}
			
	
	// 유저 페이지 -> 입양동물 검색페이징
	@GetMapping("/User/AdoptList")
	public String adoptList( String keyword, Model model, @PageableDefault(size=10,sort="id",direction = Sort.Direction.DESC)Pageable pageable) {
		Page<Adopt> adopt = null;
		
		if(keyword == null) {
			adopt = adoptService.입양목록(pageable); // 기존 리스트 페이지
		}else {
			adopt = adoptService.입양목록(pageable, keyword);
		}
		
		model.addAttribute("adopt", adopt);
		System.out.println("컨트롤 확인");
		return"Member/AdoptList";
	}
	
	
	// 입양동물 상세 페이지 
		@GetMapping("/User/adopt/{id}")
		public String findById(@PathVariable int id , Model model) {
			model.addAttribute("adopt", adoptService.입양상세(id));
			System.out.println("입양 상세 페이지");
			return"Member/AdoptDetail";
		}
		
	// 입양신청 페이지
		// 입양동물 신청 페이지
		@GetMapping("/User/adopt/{id}/SurveyForm")
		public String content(@PathVariable int id , Model modle) {
			modle.addAttribute("adopt",adoptService.입양상세(id));
			return"Member/AdoptSurveyForm";
		}
	
	
	// 유저 페이지-> 후원동물 
		//후원동물 목록
		@GetMapping("/User/SupportList")
		public String SupporList(Model model,@PageableDefault(size=10,sort = "id", direction =Sort.Direction.DESC)Pageable pageable) {
			model.addAttribute("support", supportService.후원동물목록(pageable));
			System.out.println("후원동물목록 컨트롤");
			return "Member/SupportList";
		}
		
		// 후원동물 상세 페이지
		@GetMapping("/User/support/{id}")
		public String supportDetail(Model model , @PathVariable int id) {
			model.addAttribute("support", supportService.후원상세(id));
			System.out.println("후원상세 컨트롤");
			return"Member/SupportDetail";
		}
		
		// 후원 페이지
		@GetMapping("/User/support/{id}/supportPay")
		public String supportPay(Model model, @PathVariable int id) {
			model.addAttribute("support", supportService.후원상세(id));
			return"Member/SupportPayForm";
		}
		
		// 입양후기 작성 form 
		@GetMapping("/AdoptReview")
		public String adoptReview() {
			return"Member/AdoptReview";
		}
		
		// 입양후기 
		@GetMapping("/User/AdoptReview")
		public String adoptReviewList(String keyword,Model model,@PageableDefault(size=10,sort="id",direction = Sort.Direction.DESC)Pageable pageable) {
			Page<AdoptReview> review = null;
			
			if(keyword == null) {
				review = adoptReviewService.리뷰목록(pageable); // 기존 리스트 페이지
			}else {
				review = adoptReviewService.리뷰목록(pageable, keyword);
			}
			System.out.println("입양후기 컨트롤");
			model.addAttribute("review", review);
			
			return"Member/AdoptReviewList";
		}
		
		// 후기 상세 페이지 
		@GetMapping("/User/Review/{id}")
		public String ReviewDetail(Model model , @PathVariable int id) {
			model.addAttribute("review", adoptReviewService.후기상세(id));
			System.out.println("후원상세 컨트롤");
			return"Member/ReviewtDetail";
		}
				
		// 후기 수정 페이지
		@GetMapping("/review/{id}/updateForm")
		public String updateForm(@PathVariable int id , Model model) {
			model.addAttribute("review", adoptReviewService.후기상세(id));
			System.out.println("입양수정 컨트롤");
			return"Member/ReviewUpdateForm";
		}
		// 동물 제보 작성 form 
		@GetMapping("/AnimalReport")
		public String report() {
			return "Member/ReportForm";
		}
		
		// 동물 제보 list 
		@GetMapping("/AnimalReportList")
		public String ReportList(Model model ,@PageableDefault(size=10,sort="id",direction = Direction.DESC)Pageable pageable) {
			model.addAttribute("report", animalReportService.List(pageable));
			System.out.println("제보 목록 컨트롤");
			return "Member/ReportList";
		}
		// 동물 제보 수정 페이지
		@GetMapping("/report/{id}/updateForm")
		public String update(Model model,@PathVariable int id) {
			model.addAttribute("report", animalReportService.detail(id));
			return "Member/ReportUpdateForm";
		}
		//후원 물품 list
	

}

package com.Animal.blog.Controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.Animal.blog.Config.auth.PrincipalDetail;
import com.Animal.blog.Model.Adopt;
import com.Animal.blog.Model.AdoptReview;
import com.Animal.blog.Model.KakaoProfile;
import com.Animal.blog.Model.Member;
import com.Animal.blog.Model.OAuthToken;
import com.Animal.blog.Service.AdoptReviewService;
import com.Animal.blog.Service.AdoptService;
import com.Animal.blog.Service.AnimalReportService;
import com.Animal.blog.Service.AnimalService;
import com.Animal.blog.Service.MemberService;
import com.Animal.blog.Service.SupportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// 인증이 안된 사용자들이 출립할 수 있는 경로를 /auth/**허용
// 그냥 주소가 / 이면 index.jsp 허용
// static 이하에 있는 /js/**,/css/**,/image/**

@Controller
public class MemberController {
	
	@Value("${cos.key}")
	private String cosKey;
	
	@Autowired
	private AuthenticationManager authenticationManager; // 얘를 통해 세션값 변경해야함

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
	
	
	@Autowired
	private MemberService memberService;
	
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
		
		
		
		// 카카오 로그인 
		
		@GetMapping("/auth/kakao/callback")
		public String kakaoCallback(String code) { // @ResponseBody Data를 리턴해주는 컨트롤 함수
		RestTemplate rt = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//HttpBody 오브젝트 생성 
		MultiValueMap<String, String>params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "ef61f72fe4a277bad6ae8c88f03ec6c6");
		params.add("redirect_uri","http://localhost:8088/auth/kakao/callback");
		params.add("code", code);
		  
		//HttpHeader 와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>>kakaoTokenRequest=
		new HttpEntity<>(params,headers);	
		// Http요청하기 - post방식으로 - 그리고 response 변수의 응답 받는다.
		ResponseEntity<String>response=rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
				);
		//Gson,Json Simple, ObjectMapper
		ObjectMapper ObjectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		
		try {
			oauthToken = ObjectMapper.readValue(response.getBody(),OAuthToken.class );
		} catch (JsonMappingException e) {
		
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("카카오토큰:"+oauthToken.getAccess_token());
		
		
		RestTemplate rt2 = new RestTemplate();
		// HttpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		headers2.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		
		  
		//HttpHeader 와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = 
				new HttpEntity<>(headers2);
		// Http요청하기 - post방식으로 - 그리고 response 변수의 응답 받는다.
		ResponseEntity<String>response2=rt2.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest2,
				String.class
				);
		
		System.out.println(response2.getBody());
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile=null;
		
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		// User 오브젝트 : username, password, email
				System.out.println("카카오 아이디(번호) : "+kakaoProfile.getId());
				System.out.println("카카오 이메일 : "+kakaoProfile.getKakao_account().getEmail());
				
				System.out.println("블로그서버 유저네임 : "+kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
				System.out.println("블로그서버 이메일 : "+kakaoProfile.getKakao_account().getEmail());
				// UUID란 -> 중복되지 않는 어떤 특정 값을 만들어내는 알고리즘
				System.out.println("블로그서버 패스워드 : "+cosKey);
				
				Member kakaoUser = Member.builder()
						.username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
						.password(cosKey)
						.email(kakaoProfile.getKakao_account().getEmail())
						.oauth("kakao")
						.build();
				
				// 가입자 혹은 비가입자 체크 해서 처리
				Member originUser = memberService.회원찾기(kakaoUser.getUsername());

				if(originUser.getUsername() == null) {
					System.out.println("기존 회원이 아니기에 자동 회원가입을 진행합니다");
					memberService.회원가입(kakaoUser);
				}
				
				System.out.println("자동 로그인을 진행합니다.");
				// 로그인 처리
				Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
				return "redirect:/";
			
   }
}
		

		



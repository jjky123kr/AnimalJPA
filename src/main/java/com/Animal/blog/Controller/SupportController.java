package com.Animal.blog.Controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Animal.blog.DTO.ResponseDto;
import com.Animal.blog.Service.SupportService;
import com.fasterxml.jackson.core.io.DataOutputAsStream;

@Controller
public class SupportController {

	@Autowired
	private SupportService supportService;
	
	// 후원 동물등록 페이지 
	@GetMapping("/SupportForm")
	public String SupportForm() {
		return"Support/SupportForm";
	}

	//후원동물 목록
	@GetMapping("/SupportList")
	public String SupporList(Model model,@PageableDefault(size=10,sort = "id", direction =Sort.Direction.DESC)Pageable pageable) {
		model.addAttribute("support", supportService.후원동물목록(pageable));
		System.out.println("후원동물목록 컨트롤");
		return "Support/SupportList";
	}
	// 후원 상세 페이지
	@GetMapping("/support/{id}")
	public String supportDetail(Model model , @PathVariable int id) {
		model.addAttribute("support", supportService.후원상세(id));
		System.out.println("후원상세 컨트롤");
		return"Support/SupportDetail";
	}
	// 후원 수정 페이지 
	@GetMapping("/support/{id}/updateForm")
	public String supportUpdate(Model model, @PathVariable int id) {
		model.addAttribute("support", supportService.후원상세(id));
		return"Support/SupportUpdateForm";
	}
	// 후원 페이지
	@GetMapping("/support/{id}/supportPay")
	public String supportPay(Model model, @PathVariable int id) {
		model.addAttribute("support", supportService.후원상세(id));
		return"Support/SupportPayForm";
	}
	//후원금액 결제 
	@GetMapping("/api/kakaopay")
	@ResponseBody
	public String kakaopay() {
		try {
		URL address = new URL("https://kapi.kakao.com/v1/payment/ready");
		HttpsURLConnection server = (HttpsURLConnection)address.openConnection();// 서버연결
		server.setRequestMethod("POST");
		server.setRequestProperty("Authorization","KakaoAK e38d160dcd90b15378302346069239dd");
		server.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		server.setDoOutput(true);
		String parm ="cid=TC0ONETIME&partner_order_id=partner_order_id&partner_user_id=partner_user_id&item_name=초코파이&quantity=1&total_amount=2200&vat_amount=200&tax_free_amount=0&approval_url=http://localhost:8088/success&fail_url=http://localhost:8088/fail&cancel_url=http://localhost:8088/cancel";
		OutputStream get = server.getOutputStream(); // 데이터 전송
		DataOutputStream dataget = new DataOutputStream(get);
		dataget.writeBytes(parm);
		dataget.close(); // 전송하는 역할이된다. close하는 동시에 전송
		
		int result = server.getResponseCode();
		InputStream receive ;
		if(result== 200) {
			receive = server.getInputStream(); //성공적으로 받는 코드
		}else {
			receive = server.getErrorStream();  // 실패 받는 코드
		}
		InputStreamReader reader = new InputStreamReader(receive); //읽기
		BufferedReader change = new BufferedReader(reader);
		
		return change.readLine();

		}catch(Exception e) {
			e.printStackTrace();
		}		
		return"";
	}
}


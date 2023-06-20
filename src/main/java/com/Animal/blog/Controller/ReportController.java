package com.Animal.blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Animal.blog.Service.AnimalReportService;

@Controller
public class ReportController {

	@Autowired
	private AnimalReportService animalReportService;
	
	
	// 제보 목록 
	@GetMapping("/ReportList")
	public String ReportList(Model model ,@PageableDefault(size=10,sort="id",direction = Direction.DESC)Pageable pageable) {
		model.addAttribute("report", animalReportService.List(pageable));
		System.out.println("제보 목록 컨트롤");
		return "Report/ReportList";
	}
	
	// 제보 상세 페이지
	@GetMapping("/User/report/{id}")
	public String detail(@PathVariable int id , Model model) {
		model.addAttribute("report", animalReportService.detail(id));
		return"Report/ReportDetail";
	}
	
	// 제보 수정 페이지 
	
	
	
}

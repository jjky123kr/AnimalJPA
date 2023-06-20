package com.Animal.blog.Controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.multi.MultiProgressBarUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.Animal.blog.Model.UploadFile;
import com.Animal.blog.Service.AnimalService;
import com.Animal.blog.Service.ImageService;


@Controller
public class AnimalController {

	@Autowired
	private AnimalService animalService;
	
	private ImageService imageService;
	

	// 반려동물 관리 등록 폼
	@GetMapping("/AnimalForm")
	public String WriteForm() {
		return "Animal/AnimalForm";
	}
	

	// 반려동물 목록 
	@GetMapping("/AnimalList")
	public String index(Model model,@PageableDefault(size=10,sort="animalid",direction = Sort.Direction.DESC)Pageable pageable) { 	
		model.addAttribute("animals",animalService.글목록(pageable)); 
		System.out.println("컨트롤 확인 테스트 입니다.");
		return"Animal/AnimalList";
	}
	
	
   // 반려동물 상세페이지 정보 
	@GetMapping("/animal/{id}")
	public String findByid(@PathVariable int id , Model model) {
		
		model.addAttribute("animal",animalService.글상세보기(id));
		return "Animal/AnimalDetail";
		
	}

	// 동물 정보 수정 페이지
		@GetMapping("/animal/{id}/updateForm")
		public String updateForm(@PathVariable int id , Model model) {
			model.addAttribute("animal", animalService.글상세보기(id));
			return "Animal/AnimalUpdateForm";
		}
		
	

  
}

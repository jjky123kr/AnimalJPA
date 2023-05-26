package com.Animal.blog.Conetroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Animal.blog.Model.UploadFile;
import com.Animal.blog.Service.AnimalService;
import com.Animal.blog.Service.ImageService;

@Controller
public class AdminController {

	@Autowired
	private AnimalService animalService;
	
	private ImageService imageService;


	// 반려동물 관리 등록 폼
	@GetMapping("/AnimalForm")
	public String WriteForm() {
		return "Admin/AnimalForm";
	}
	

	// 반려동물 목록
	@GetMapping("/admin")
	public String index(Model model,@PageableDefault(size=10,sort="animalid",direction = Sort.Direction.DESC)Pageable pageable) { 	
		model.addAttribute("animals",animalService.글목록(pageable)); 
		return"Admin/main";
	}
	
   // 반려동물 상세페이지 정보 
	@GetMapping("/animal/{id}")
	public String findByid(@PathVariable int id , Model model) {
		
		model.addAttribute("animal",animalService.글상세보기(id));
		return "Admin/AnimalDetail";
		
	}

	// 동물 정보 수정 페이지
		@GetMapping("/animal/{id}/updateForm")
		public String updateForm(@PathVariable int id , Model model) {
			model.addAttribute("animal", animalService.글상세보기(id));
			return "Admin/AnimalUpdateForm";
		}
	

	
	/*
	 * @GetMapping("/adminList") public String animalList(Model model,
	 * 
	 * @PageableDefault(size =10, sort = "animal_id", direction =
	 * Sort.Direction.DESC) Pageable pageable) { model.addAttribute("animalList",
	 * animalService.글목록(pageable)); return "Admin/main"; }
	 */

	// 입양 절차 등록

} 

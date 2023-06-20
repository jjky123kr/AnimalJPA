package com.Animal.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	//입양 절차 등록 폼
	   @GetMapping("/AdoptForm")
	   public String adopt() {
		   return"Admin/AdoptForm";
	  }
	   
}

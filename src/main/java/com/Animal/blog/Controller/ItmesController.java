package com.Animal.blog.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.Animal.blog.Model.DonationItems;
import com.Animal.blog.Service.ItemsService;
import com.Animal.blog.repository.ItemsRepository;

@Controller
public class ItmesController {

	@Autowired
	private ItemsService itemsService;

	// 물품 등록페이지
	@GetMapping("/SupportThing")
	public String itme() {
		return "Items/ItemForm";
	}

	// 물품 리스트
	@GetMapping("/ItemsList")
	public String itemList(Model model, @PageableDefault(size = 10, sort = "createDate", direction = Sort.Direction.ASC) Pageable pageable) {
	    List<DonationItems> pinnedItems = itemsService.getPinnedItems(); // Get pinned items
	    List<DonationItems> paginatedItems = itemsService.getList(pageable).getContent(); // Get paginated items

	    model.addAttribute("pinnedItems", pinnedItems);
	    model.addAttribute("paginatedItems", paginatedItems);

	    return "Items/ItemsList";
	}

	// 물품 상세 페이지
	
	@GetMapping("/items/{id}")
	public String detail(Model model ,@PathVariable int id) {
		model.addAttribute("items", itemsService.detail(id));
		
		return "Items/ItmeDetail";
	}
	
	// 수정 페이지 
	@GetMapping("/itmes/{id}/updateForm")
    public String update(Model model, @PathVariable int id) {
		model.addAttribute("itmes", itemsService.detail(id));
		return"Items/ItemUpdate";
		
	}

}

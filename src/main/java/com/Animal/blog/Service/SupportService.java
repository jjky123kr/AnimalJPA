package com.Animal.blog.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Animal.blog.Model.Support;
import com.Animal.blog.repository.SupportRepository;

@Service
public class SupportService {

	@Autowired
	
	private SupportRepository supportRepository;
	
	// 후원동물 등록
	@Transactional
	public void 등록(Support support) {
     support.setCount(0);
     System.out.println("후원동물 등록 서비스");
     supportRepository.save(support);
	}

	// 후원동물 목록
	@Transactional(readOnly = true)
	public Page<Support>후원동물목록(Pageable pageable) {
		System.out.println("후원동물 목록 서비스");
	 return	supportRepository.findAll(pageable);
	}

	// 후원상세 
	@Transactional(readOnly = true)
	public Support 후원상세(int id) {    
		return supportRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("후원:id를 찾을수 없습니다.");
		});
	}
    // 후원동물 수정
	@Transactional
	public void 후원수정(Support supportRequest, int id) {
	  
		Support support = supportRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정을 실패 했어요");		
			});	// 영속화
		System.out.println("후원동물 수정 서비스");
		support.setTitle(supportRequest.getTitle());
		support.setName(supportRequest.getName());
		support.setProfileImage(supportRequest.getProfileImage());
		support.setContent(supportRequest.getContent());
		
	}
	// 후원삭제 
	public Support findById(int id) { // 삭제 id 값을 찾는다. 
		
		return supportRepository.findById(id).
				orElseThrow(() -> new IllegalArgumentException("해당 아이디의 입양 정보를 찾을 수 없습니다: " + id));
	}

	public void 후원삭제(int id) {
		supportRepository.deleteById(id);
		
	}

}

package com.Animal.blog.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Animal.blog.DTO.UploadResponseDto;
import com.Animal.blog.Model.Adopt;
import com.Animal.blog.Model.AdoptionStatus;
import com.Animal.blog.Model.Animal;
import com.Animal.blog.Model.Member;
import com.Animal.blog.Model.UploadFile;
import com.Animal.blog.repository.AdoptRepository;
import com.Animal.blog.repository.UploadFileRepository;

@Service
public class AdoptService {

	@Autowired
	private AdoptRepository adoptRepository;
	
	public void save(Adopt adopt ,Member member) {		
		adopt.setCount(0);
		adopt.setMember(member);
		adopt.setAdoptionStatus(AdoptionStatus.PENDING); // 입양중
		System.out.println("save:호출");	
		 adoptRepository.save(adopt);
		
	}
	// 입양목록 페이지
	public Page<Adopt> 입양목록(Pageable pageable) {
		
		return adoptRepository.findAll(pageable);
	}
	// 입양검색목록 페이지
	@Transactional(readOnly = true)
	public Page<Adopt> 입양목록(Pageable pageable, String keyword) {
		System.out.println("service 에 들어옴");
		return adoptRepository.findByTitleContaining(keyword,pageable);
	}
	

	// 입양 상세 페이지
	public Adopt 입양상세(int id) {
		return adoptRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("입양:id를 찾을수 없습니다.");
		});
	}
	

	// 입양 수정
	@Transactional
	public void update(Adopt requestAdopt, int id) {
		System.out.println("수정Service 호출");		
		Adopt adopt = adoptRepository.findById(id)
				.orElseThrow(()->{
		return new IllegalArgumentException("수정을 실패 했어요");
			
		});	
		adopt.setAge(requestAdopt.getAge());
		System.out.println("나이"+requestAdopt.getAge());
		adopt.setKg(requestAdopt.getKg());
		adopt.setHealth(requestAdopt.getHealth());
		adopt.setPersonality(requestAdopt.getPersonality());	
		adopt.setContent(requestAdopt.getContent());
		System.out.println("내용"+requestAdopt.getContent());
	}
	
	// 삭제 
	@Transactional
	public Adopt findById(int id) {
	     System.out.println("삭제Service 호출");	
		return adoptRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 아이디의 입양 정보를 찾을 수 없습니다: " + id));
}
	public void 글삭제(int id) {
		adoptRepository.deleteById(id);	
	}

	

	
}

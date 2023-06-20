package com.Animal.blog.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Animal.blog.Config.auth.PrincipalDetail;
import com.Animal.blog.Model.AnimalReport;
import com.Animal.blog.Model.Member;
import com.Animal.blog.repository.AnimalReportRepository;

@Service
public class AnimalReportService {

	@Autowired
	private AnimalReportRepository animalReportRepository;
	// 제보 등록
	public void save(AnimalReport animalReport, Member member) {
		animalReport.setMember(member);
		animalReportRepository.save(animalReport);

		
	}
	// 제보 목록
	public Page<AnimalReport> List(Pageable pageable) {
		System.out.println("제보 목록 서비스");
		return animalReportRepository.findAll(pageable);
	}
	// 제보 상세보기
	public AnimalReport detail(int id) {
		return animalReportRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("입양신청:id를 찾을수 없습니다.");
		});
	}
	// 제보 수정 
	public void update(AnimalReport RequestanimalReport, int id) {
	     AnimalReport animalReport = animalReportRepository.findById(id).orElseThrow(()->{
	    	 return new IllegalArgumentException("입양신청:id를 찾을수 없습니다.");
	     });
	        System.out.println("제보수정 서비스");
	        
	         animalReport.setTitle(RequestanimalReport.getTitle());	      
	         animalReport.setName(RequestanimalReport.getName());
	         System.out.println("이름:"+RequestanimalReport.getName());
	         animalReport.setContent(RequestanimalReport.getContent());
	         
	         animalReportRepository.save(animalReport);
	}
	// 글삭제 번호
	public AnimalReport findById(int id) {
		
		return animalReportRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("입양신청:id를 찾을수 없습니다.");
		});
	}
	// 글삭제 코드
	public void 글삭제(int id) {
		animalReportRepository.deleteById(id);	
		
	}

	
	
	// 제보 삭제 
}

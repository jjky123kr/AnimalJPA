package com.Animal.blog.Service;

import java.beans.Transient;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Animal.blog.Model.Adopt;
import com.Animal.blog.Model.AdoptSurvey;
import com.Animal.blog.Model.AdoptionStatus;
import com.Animal.blog.repository.AdoptRepository;
import com.Animal.blog.repository.AdoptSurveyRepository;

@Service
public class AdoptSurveyService {

	@Autowired
	private AdoptSurveyRepository adoptSurveyRepository;
	
	@Autowired
	private AdoptRepository adoptRepository;

	// 설문조사 등록
	@Transient
	public void 설문조사(AdoptSurvey adoptSurvey, int adoptId) { 
		System.out.println("입양신청 서비스");
		System.out.println("adoptId"+adoptId); // adopt 입양동물 번호 값
		Adopt adopt = adoptRepository.findById(adoptId).orElseThrow(()->{
			return new IllegalArgumentException(": 게시글 id를 찾을 수 없습니다.");
		}); // 영속화 완료
		
		adoptSurvey.setAdopt(adopt);
		adopt.setAdoptionStatus(AdoptionStatus.PENDING);
		adoptSurveyRepository.save(adoptSurvey);		    
 }

	// 설문조사 목록
	@Transactional(readOnly = true)
	public Page<AdoptSurvey>입양신청목록(Pageable pageable) {
		System.out.println("입양목록");
		return adoptSurveyRepository.findAll(pageable);
	}

	// 입양신청 상세
	public AdoptSurvey 입양신청상세(int id) {
		return adoptSurveyRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("입양신청:id를 찾을수 없습니다.");
		});
	}

	// 입양신청 삭제 
	public AdoptSurvey findById(int id) {
		System.out.println("입양신청 삭제 서비스");
		return adoptSurveyRepository.findById(id) // 번호값 찾기
				.orElseThrow(() -> new IllegalArgumentException("해당 아이디의 입양 정보를 찾을 수 없습니다: " + id));	
	}

	public void 글삭제(int id) {
	       adoptSurveyRepository.deleteById(id);
		
	}
	
	
	// 승인 처리 
	@Transactional
    public void completeAdoption(int id) {
        AdoptSurvey adoptSurvey = adoptSurveyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid surveyId: " + id));
        
        // 입양 상태를 "입양완료"로 변경
        adoptSurvey.setAdoptionStatus(AdoptionStatus.COMPLETED);

        // 저장 등의 추가 로직 수행
         adoptSurveyRepository.save(adoptSurvey);
    }
	
}



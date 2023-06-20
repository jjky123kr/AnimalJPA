package com.Animal.blog.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Animal.blog.Config.auth.PrincipalDetail;
import com.Animal.blog.Model.AdoptReview;
import com.Animal.blog.Model.Member;
import com.Animal.blog.repository.AdoptRepository;
import com.Animal.blog.repository.AdoptReviewRepository;

@Service
public class AdoptReviewService {

	@Autowired
	private AdoptReviewRepository adoptReviewRepository;

	public void save(AdoptReview adoptReview, Member member) {
		adoptReview.setCount(0);
		adoptReview.setMember(member);
		adoptReviewRepository.save(adoptReview);
	}

	// 리뷰 페이징(검색 하지 않을 경우)
	@Transactional(readOnly = true)
	public Page<AdoptReview> 리뷰목록(Pageable pageable) {
		return adoptReviewRepository.findAll(pageable);
	}

	// 리뷰 페이징(검색을 했을 경우)
	@Transactional(readOnly = true)
	public Page<AdoptReview> 리뷰목록(Pageable pageable, String keyword) {

		return adoptReviewRepository.findByTitle(keyword, pageable);

	}

	// 리뷰 상세 페이지
	public Object 후기상세(int id) {

		return adoptReviewRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("후기:id를 찾을수 없습니다.");
		});
	}

	// 리뷰 수정
	@Transactional
	public void 리뷰수정(int id, AdoptReview adoptReviews) {
		AdoptReview adoptReview = adoptReviewRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("후기:id를 찾을수 없습니다.");
		}); // 영속화
		adoptReview.setTitle(adoptReviews.getTitle());
		adoptReview.setContent(adoptReviews.getContent());
	}
	
   // 글삭제 번호 찾기
	@Transactional
	public AdoptReview findById(int id) {
	    
		return adoptReviewRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("후기:id를 찾을수 없습니다.");
		});
	}

	public void 글삭제(int id) {
      adoptReviewRepository.deleteById(id);
	}

}

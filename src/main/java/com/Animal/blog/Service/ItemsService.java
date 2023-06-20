package com.Animal.blog.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Animal.blog.Controller.ItmesController;
import com.Animal.blog.Model.DonationItems;
import com.Animal.blog.Model.Member;
import com.Animal.blog.repository.ItemsRepository;

@Service
public class ItemsService {

	@Autowired
	private ItemsRepository itemsRepository;
	//물품 게시물 등록
	public void save(DonationItems doationItmes ,Member member) {
		
		doationItmes.setMember(member);
		itemsRepository.save(doationItmes);
	}
	
	// 글 목록 
	// 상단에 고정된 게시물 가져오기
	public List<DonationItems> getPinnedItems() {
        return itemsRepository.findByIsPinnedOrderByCreateDateDesc(true);
    }
    // 일반 게시물
	public Page<DonationItems> getList(Pageable pageable) { 
        return itemsRepository.findAll(pageable);
    }

    //글 상세보기
	public DonationItems detail(int id) {
		return itemsRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("입양신청:id를 찾을수 없습니다.");
		});
	}

	// 글 수정
	public void update(DonationItems ReqdonationItems, int id) {
		DonationItems donationItems = itemsRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("입양신청:id를 찾을수 없습니다.");
		}); // 영속화
		
		donationItems.setTitle(ReqdonationItems.getTitle());
		donationItems.setName(ReqdonationItems.getName());
		donationItems.setNoticeOption(ReqdonationItems.getNoticeOption());
		donationItems.setContent(ReqdonationItems.getContent());
		System.out.println("수정 서비스 ");
		itemsRepository.save(donationItems);
	}

	// 삭제할 글 번호 찾기
	public DonationItems findById(int id) {
		return itemsRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 아이디의 입양 정보를 찾을 수 없습니다: " + id));
    }
	
    // 글 삭제
	public void 글삭제(int id) {
		// TODO Auto-generated method stub
		itemsRepository.deleteById(id);
	}	
}

	



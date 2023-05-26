package com.Animal.blog.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Animal.blog.Model.Board;
import com.Animal.blog.Model.Member;
import com.Animal.blog.repository.BoardRepository;
@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	// 글쓰기
	@Transactional
	public void 글쓰기(Board board, Member member) {
	board.setCount(0);
	board.setMember(member);
	boardRepository.save(board);
		
	}
	
	// 글 목록
	@Transactional(readOnly=true)
	public Page<Board>글목록(Pageable pageable){
	return 	boardRepository.findAll(pageable);
	}
	
}
	




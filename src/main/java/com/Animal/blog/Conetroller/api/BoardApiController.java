package com.Animal.blog.Conetroller.api;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Animal.blog.Config.auth.PrincipalDetail;
import com.Animal.blog.DTO.ResponseDto;
import com.Animal.blog.Model.Board;
import com.Animal.blog.Service.BoardService;
@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardservice;
	
	// 글작성
	@PostMapping("/api/board")
	public ResponseDto<Integer>save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
		boardservice.글쓰기(board,principal.getMember());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
}

package com.Animal.blog.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Animal.blog.Model.Member;
import com.Animal.blog.Model.RoleType;
import com.Animal.blog.repository.MemberRepository;
@Service
public class MemberService {

	@Autowired
	private MemberRepository  memberRepository;
	
	
	@Autowired
	  private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void 회원가입(Member member) {
		
		String rawPassword =member.getPassword(); // 1234
		String encPassword = encoder.encode(rawPassword); // 해쉬
		member.setPassword(encPassword);
		member.setRole(RoleType.USER);
				
		memberRepository.save(member);
	}
	

	// 회원목록
	public List<Member>회원목록() {
		// TODO Auto-generated method stub
		return memberRepository.findAll();
	}


	public boolean isDuplicatedUsername(String username) {
		 Optional<Member> existingMember = memberRepository.findByUsername(username);
	        return existingMember.isPresent();
	}

	
	

}

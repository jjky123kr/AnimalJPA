package com.Animal.blog.Config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Animal.blog.Model.Member;
import com.Animal.blog.repository.MemberRepository;

@Service
public class PrincipalDetailService implements UserDetailsService{

	@Autowired
	private MemberRepository memberRepository;
	
	//스프링이 로그인 요청을 가로챌때 ,username, password 변수 2개 가로챈다.
	//password 부분은 알아서 처리 해준다.
	
	
	// username 이 DB에 있는지 확인해주어야 한다.
	// user를 리턴 할 수 없어서, PrincipalDetail 리턴해준다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member principa = memberRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당사용자를 찾을수 없습니다."+username);
				});
		return new PrincipalDetail(principa); // 시큐리티의 세션에 유저 정보 저장된다. 
		                                                          // 이때 타입이 UserDetails 타이다.
	}


}

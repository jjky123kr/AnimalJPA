package com.Animal.blog.Config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Animal.blog.Model.Member;

import lombok.Getter;

@Getter
public class PrincipalDetail implements UserDetails {

	private Member member;  // 객체를 품다(콤포지션)

  public PrincipalDetail(Member member) {  // 생성자 등록
	  this.member=member;
  }
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return member.getUsername();
	}

	// 계정이 만료되지 않았는지 리턴한다. (treu=만료안됨)
	@Override
	public boolean isAccountNonExpired() {
	
		return true;
	}
	// 계정이 만료되지 않았는지 리턴 한다.(true :잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	// 비밀번호가 만료되지 않았는지 리턴한다.(true:만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}
	
	// 계정 활성화(사용가능)인지 리턴(true="만료안됨")
	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
	
	// 계정이 갖고있는 권한 목록을 리턴한다. (권한이 여러개 있을 경우 루프를 돌려서 한다.)
	// 계정이 갖고있는 권한 목록을 리턴한다.(권한이 여러개 있을 수 있어서 루프를 들어야 하는데 우리는 한개만)
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				
				Collection<GrantedAuthority>collectors=new ArrayList<>();
//				collectors.add(new GrantedAuthority() {
//					
//					@Override
//					public String getAuthority() {
//						// TODO Auto-generated method stub
//						return "ROLE_"+user.getRole(); //ROLE_USER
//					}
//				});
				
				// 람다식 사용
				collectors.add(()->{return"ROLE_"+member.getRole();});
				return collectors;
			}
		    
	
	
}

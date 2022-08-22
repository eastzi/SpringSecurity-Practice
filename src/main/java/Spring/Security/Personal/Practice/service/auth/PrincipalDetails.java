package Spring.Security.Personal.Practice.service.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import Spring.Security.Personal.Practice.domain.user.User;

public class PrincipalDetails implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private User user;
	
	public PrincipalDetails(User user) {
		this.user = user;
	}

	//사용자 계정(id)이 가지고 있는 권한을 list로 목록을 리턴함. 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		
		List<String> roleList = user.getUserRoles();
		
		/*
		 * 향상된 for문
		 *
		 * for(타입 변수명 : 배열 or 컬렉션) {
		 * 		반복할 문장
		 * }
		 * 
		 * 1. 타입은 배열 or 컬렉션의 타입이어야 함.
		 * 2. 배열 or 컬렉션에 저장된 요소들을 읽어오는 용도로만 사용  
		 */
		for(String role : roleList) { 
			GrantedAuthority authority = new GrantedAuthority() {
				
				private static final long serialVersionUID = 1L;

				@Override
				public String getAuthority() {
					return role;
				}
			};
			
			grantedAuthorities.add(authority);
		}
		
		return grantedAuthorities;
		
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getUser_password();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUser_id();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}

package Spring.Security.Personal.Practice.service.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Spring.Security.Personal.Practice.domain.user.User;
import Spring.Security.Personal.Practice.domain.user.UserRepository;
import Spring.Security.Personal.Practice.web.dto.auth.SignupReqDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{

	private final UserRepository userRepository; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User userEntity = null;
		
		try {
			userEntity = userRepository.findUserByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(username);
		}
		
		if(userEntity == null) {
			throw new UsernameNotFoundException(username + "사용자 이름은 사용할 수 없습니다.");
		}
		
//		UserDetails userDetails = new UserDetails() {
//			
//			@Override
//			public boolean isEnabled() {
//				// TODO Auto-generated method stub
//				return true;
//			}
//			
//			@Override
//			public boolean isCredentialsNonExpired() {
//				// TODO Auto-generated method stub
//				return true;
//			}
//			
//			@Override
//			public boolean isAccountNonLocked() {
//				// TODO Auto-generated method stub
//				return true;
//			}
//			
//			@Override
//			public boolean isAccountNonExpired() {
//				// TODO Auto-generated method stub
//				return true;
//			}
//			
//			@Override
//			public String getUsername() {
//				// TODO Auto-generated method stub
//				return "test";
//			}
//			
//			@Override
//			public String getPassword() {
//				// TODO Auto-generated method stub
//				return new BCryptPasswordEncoder().encode("1234");
//			}
//			
//			@Override
//			public Collection<? extends GrantedAuthority> getAuthorities() {
//				// TODO Auto-generated method stub
//				return null;
//			}
//		};
//			
//		return userDetails;
		return new PrincipalDetails(userEntity);
	}
	
//	public boolean addUser() {
//		User user = User.builder()
//						.user_name("이스트지")
//						.user_email("sdf@naver.com")
//						.user_id("eastzi")
//						.user_password(new BCryptPasswordEncoder().encode("1234"))
//						.user_roles("ROLE_USER, ROLE_MANAGER")
//						.build();
//	}
	
	public boolean addUser(SignupReqDto signupReqDto) throws Exception {
		
		
//		try {
//			userRepository.save(user);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
		
		return userRepository.save(signupReqDto.toEntity()) > 0;
	}
	

}

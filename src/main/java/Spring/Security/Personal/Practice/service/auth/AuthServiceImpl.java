package Spring.Security.Personal.Practice.service.auth;

import org.springframework.stereotype.Service;

import Spring.Security.Personal.Practice.domain.user.UserRepository;
import Spring.Security.Personal.Practice.web.dto.auth.UsernameCheckReqDto;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	
	@Override
	public boolean checkUsername(UsernameCheckReqDto usernameCheckReqDto) throws Exception {
		// TODO Auto-generated method stub
		return userRepository.findUserByUsername(usernameCheckReqDto.getUsername()) == null;
	}

	@Override
	public boolean signup() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}

package Spring.Security.Personal.Practice.web.dto.auth;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import Spring.Security.Personal.Practice.domain.user.User;
import lombok.Data;

@Data
public class SignupReqDto {

	//회원가입할 때 제약사항들
	
	@NotBlank
	@Pattern(regexp = "^[가-힇]*$", message = "한글만 입력 가능합니다.")
	private String name;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,12}$")
	private String username;
	
	@NotBlank
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[-~!@#$%^&*_+=])[a-zA-Z\\d-~!@#$%^&*_+=]{8,16}$")
	private String password;
	
	@AssertTrue(message="아이디 중복확인이 되지 않았습니다.") //true인가? true가 아니면 오류발생
	private boolean checkUsernameFlag;
	
	//db에 존재하는 column들을 필드로 가지는 객체 
	//db와 1 대 1 대응이므로, db에 없는 column을 필드로 가지면 안된다. 
	public User toEntity() {
		return User.builder()
				.user_name(name)
				.user_email(email)
				.user_id(username)
				.user_password(new BCryptPasswordEncoder().encode(password))
				.user_roles("ROLE_USER")
				.build();
	}
}

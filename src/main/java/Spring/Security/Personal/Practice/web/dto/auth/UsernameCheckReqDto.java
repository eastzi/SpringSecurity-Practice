package Spring.Security.Personal.Practice.web.dto.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UsernameCheckReqDto {
	
	@NotBlank
	@Size(max = 16, min = 4, message = "4자 이상 6자 이하")
	private String username;
}

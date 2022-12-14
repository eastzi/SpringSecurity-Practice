package Spring.Security.Personal.Practice.web.controller.api;

import java.util.HashMap;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Spring.Security.Personal.Practice.service.auth.AuthService;
import Spring.Security.Personal.Practice.service.auth.PrincipalDetailsService;
import Spring.Security.Personal.Practice.web.dto.CMRespDto;
import Spring.Security.Personal.Practice.web.dto.auth.SignupReqDto;
import Spring.Security.Personal.Practice.web.dto.auth.UsernameCheckReqDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final PrincipalDetailsService principalDetailsService;
	private final AuthService authService;
	
	@GetMapping("/signup/validation/username")
	public ResponseEntity<?> checkUsername(@Valid UsernameCheckReqDto usernameCheckReqDto, BindingResult bindingResult) {
		
//		if(bindingResult.hasErrors()) {
//			Map<String, String> errorMessage = new HashMap<String, String>();
//			
//			bindingResult.getFieldErrors().forEach(error -> {
////				System.out.println("오류발생 필드명: " + error.getField());
////				System.out.println("오류발생 상세메시지: " + error.getDefaultMessage());
//				errorMessage.put(error.getField(), error.getDefaultMessage());
//			});
//		
//			return ResponseEntity.badRequest().body(new CMRespDto<>(-1, "유효성 검사실패", errorMessage));
//		}
		//return ResponseEntity.ok(new CMRespDto<>(1, "사용가능", true));
		
		boolean status = false;
		
		try {
			status = authService.checkUsername(usernameCheckReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "서버오류", status));
		}
		
		return ResponseEntity.ok(new CMRespDto<>(1, "회원가입 가능여부", status));
	}

	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody @Valid SignupReqDto signupReqDto, BindingResult bindingResult) {
		boolean status = false;
	
//		if(bindingResult.hasErrors()) {
//			Map<String, String> errorMessage = new HashMap<String, String>();
//			
//			bindingResult.getFieldErrors().forEach(error -> {
//				errorMessage.put(error.getField(), error.getDefaultMessage());
//			});	
//			
//			return ResponseEntity.badRequest().body(new CMRespDto<>(-1, "유효성 검사실패", errorMessage));
//		}
		
		try {
			status = principalDetailsService.addUser(signupReqDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "회원가입 실패", status));
		}
		
		return ResponseEntity.ok(new CMRespDto<>(1, "회원가입 성공", status));
		
	}
	
}	


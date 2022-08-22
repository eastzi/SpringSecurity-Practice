package Spring.Security.Personal.Practice.web.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/grant/test")
public class GrantTestController {
	
	@GetMapping("/user")
	public ResponseEntity<?> getUserDate() {
		return ResponseEntity.ok("ROLE_USER 권한을 가지고 있음.");
	}
	
	@GetMapping("/manager")
	public ResponseEntity<?> getManagerDate() {
		return ResponseEntity.ok("ROLE_MANAGER 권한을 가지고 있음.");
	}
	
	@GetMapping("/admin")
	public ResponseEntity<?> getAdminDate()	{
		return ResponseEntity.ok("ROLE_ADMIN 권한을 가지고 있음.");
	}
}

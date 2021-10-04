package nhom8.javabackend.hotel.welcome;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	
	@GetMapping("/welcome")
	private Object welcome() {
		return "Welcome to hotel booking app.";
	}
}

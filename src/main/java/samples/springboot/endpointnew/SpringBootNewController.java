package samples.springboot.endpointnew;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringBootNewController {
	
	@GetMapping("/")
	public String getHome() {
		return "Here is Home!";
	}
	 
}

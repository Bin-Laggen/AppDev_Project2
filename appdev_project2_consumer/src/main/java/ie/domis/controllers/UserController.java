package ie.domis.controllers;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import ie.domis.domain.User;

@Controller
public class UserController extends BaseController {
	
	@GetMapping(value={"/users", "/", "/index"})
	public String showUsers(Model model) {
		RestTemplate restTemplate = new RestTemplate();
		String URL = "http://localhost:8080/api/users";

		String username = "pat@email.com";
		String password = "patPass";
		HttpHeaders headers = createBasicAuthHeaders(username, password);
		
		ResponseEntity<List<User>> responseEntity =
				restTemplate.exchange(URL,
									  HttpMethod.GET,
									  new HttpEntity<>(headers),
									  new ParameterizedTypeReference<List<User>>() {});
		List<User> users = responseEntity.getBody();
		
		model.addAttribute("users", users);
		return "index";
	}
}

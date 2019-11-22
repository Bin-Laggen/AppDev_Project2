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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import ie.domis.domain.Bid;

@Controller
public class BidController extends BaseController {
	
	@GetMapping("/bids/{userId}")
	public String showUserBids(@PathVariable(name = "userId") int userId, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		String URL = "http://localhost:8080/api/bids/" + userId;
		
		String username = "pat@email.com";
		String password = "patPass";
		HttpHeaders headers = createBasicAuthHeaders(username, password);
		
		ResponseEntity<List<Bid>> responseEntity =
				restTemplate.exchange(URL,
									  HttpMethod.GET,
									  new HttpEntity<>(headers),
									  new ParameterizedTypeReference<List<Bid>>() {});
		List<Bid> bids = responseEntity.getBody();
		
		model.addAttribute("bids", bids);
		return "bids";
	}
}

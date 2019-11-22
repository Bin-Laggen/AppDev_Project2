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

import ie.domis.domain.Job;

@Controller
public class JobController extends BaseController {
	
	@GetMapping("/jobs")
	public String showJobs(Model model) {
		RestTemplate restTemplate = new RestTemplate();
		String URL = "http://localhost:8080/api/jobs";

		String username = "pat@email.com";
		String password = "patPass";
		HttpHeaders headers = createBasicAuthHeaders(username, password);
		
		ResponseEntity<List<Job>> responseEntity =
				restTemplate.exchange(URL,
									  HttpMethod.GET,
									  new HttpEntity<>(headers),
									  new ParameterizedTypeReference<List<Job>>() {});
		List<Job> jobs = responseEntity.getBody();
		
		model.addAttribute("jobs", jobs);
		return "jobs";
	}
}

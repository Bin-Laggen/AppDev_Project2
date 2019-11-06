package ie.domis.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
//	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping(value= {"/"})
	public String handleIndexRequest() {
		return "index";
	}

}

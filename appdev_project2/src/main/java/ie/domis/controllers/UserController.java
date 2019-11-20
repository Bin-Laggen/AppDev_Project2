package ie.domis.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ie.domis.domain.Job;
import ie.domis.domain.User;
import ie.domis.forms.JobForm;
import ie.domis.service.JobService;
import ie.domis.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	JobService jobService;

	@Autowired
	UserService userService;

	@GetMapping(value= {"/users"})
	public String handleUsersRequest(Model model, Principal user) {
		User loggedInUser = userService.findByEmail(user.getName());
		model.addAttribute("user", loggedInUser);
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "users";
	}
	
	@GetMapping(value= {"/account"})
	public String handleAccountRequest(Model model, Principal user) {
		User loggedInUser = userService.findByEmail(user.getName());
		model.addAttribute("user", loggedInUser);
		List<Job> jobs = jobService.findAllOwnersJobs(loggedInUser.getUserId());
		model.addAttribute("jobs", jobs);
		JobForm jobForm = new JobForm();
		jobForm.setOwnerId(loggedInUser.getUserId());
		model.addAttribute("jobForm", jobForm);
		return "account";
	}

}

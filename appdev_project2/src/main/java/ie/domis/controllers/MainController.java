package ie.domis.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ie.domis.domain.Bid;
import ie.domis.domain.Job;
import ie.domis.domain.User;
import ie.domis.service.BidService;
import ie.domis.service.JobService;
import ie.domis.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	JobService jobService;
	
	@Autowired
	BidService bidService;
	
	@Autowired
	UserService userService;
	
	@GetMapping(value= {"/", "/index"})
	public String handleIndexRequest(Model model, Principal user) {
		User loggedInUser = userService.findByEmail(user.getName());
		model.addAttribute("user", loggedInUser);
		List<Job> jobs = jobService.findAllJobs();
		model.addAttribute("jobs", jobs);
		return "index";
	}
	
	@GetMapping(value= {"/job/{id}"})
	public String handleJobRequest(@PathVariable("id") int id, Model model, Principal user) {
		User loggedInUser = userService.findByEmail(user.getName());
		model.addAttribute("user", loggedInUser);
		Job job = jobService.findJobById(id);
		System.out.println(job);
		if (job == null) {
			model.addAttribute("id", id);
			return "doesnotexist";
		}
		List<Bid> bids = bidService.findAllBidsByJob(id);
		model.addAttribute("job", job);
		model.addAttribute("bids", bids);
		return "job";
	}
	
	@GetMapping(value= {"/users"})
	public String handleUsersRequest(Model model, Principal user) {
		User loggedInUser = userService.findByEmail(user.getName());
		model.addAttribute("user", loggedInUser);
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "users";
	}
	
	@GetMapping(value= {"/user/{id}"})
	public String handleAccountRequest(@PathVariable("id") int id, Model model, Principal user) {
		User loggedInUser = userService.findByEmail(user.getName());
		model.addAttribute("user", loggedInUser);
		return "account";
	}
	
	@GetMapping(value= {"/register"})
	public String handleRegistrationRequest() {
		return "register";
	}
	
	@GetMapping(value= {"/login"})
	public String handleLoginRequest() {
		return "login";
	}

}

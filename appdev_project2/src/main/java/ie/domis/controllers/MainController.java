package ie.domis.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ie.domis.domain.Bid;
import ie.domis.domain.Job;
import ie.domis.domain.Role;
import ie.domis.domain.User;
import ie.domis.forms.UserForm;
import ie.domis.service.BidService;
import ie.domis.service.JobService;
import ie.domis.service.RoleService;
import ie.domis.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	JobService jobService;
	
	@Autowired
	BidService bidService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	PasswordEncoder passEnc;
	
	
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
	public String handleRegistrationRequest(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "register";
	}
	
	@PostMapping(value= {"/register"})
	public String registerNewUser(@Valid UserForm userForm, BindingResult binding) {
		if (!userForm.getPassword().equals(userForm.getPassword2())) {
			binding.addError(new FieldError("userForm", "password2", "Passwords must match"));
		}
		if (binding.hasErrors()) {
			return "register";
		}
		Role newRole = new Role(userForm.getEmail(), "ROLE_USER");
		newRole = roleService.addRole(newRole);
		if (newRole == null) {
			return"redirect:register";
		}
		User newUser = new User(userForm.getEmail(), passEnc.encode(userForm.getPassword()), userForm.getName(), userForm.getSurname(), userForm.getPhoneNumber(), newRole, true);
		newUser = userService.addUser(newUser);
		if (newUser == null) {
			return "redirect:register";
		}
		return "redirect:/user/" + newUser.getUserId();
		
	}
	
	@GetMapping(value= {"/login"})
	public String handleLoginRequest() {
		return "login";
	}

}

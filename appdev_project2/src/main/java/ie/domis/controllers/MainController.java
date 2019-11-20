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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ie.domis.domain.Job;
import ie.domis.domain.Role;
import ie.domis.domain.User;
import ie.domis.forms.SearchForm;
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
		SearchForm searchForm = new SearchForm();
		model.addAttribute("searchForm", searchForm);
		return "index";
	}
	
	@PostMapping(value = {"/search"})
	public String handleSearchRequest(@Valid SearchForm searchForm, BindingResult binding, RedirectAttributes redirectAttributes, Model model, Principal user) {
		if (binding.hasErrors()) {
			return "redirect:/index";
		}
		List<Job> matchingJobs = jobService.findJobsContainingPhrase(searchForm.getSearchPhrase());
		if (matchingJobs.isEmpty()) {
			return "redirect:/index";
		}
		User loggedInUser = userService.findByEmail(user.getName());
		model.addAttribute("user", loggedInUser);
		model.addAttribute("jobs", matchingJobs);
		model.addAttribute("showHome", true);
		searchForm = new SearchForm();
		model.addAttribute("searchForm", searchForm);
		return "index";
	}
	
	@GetMapping(value= {"/register"})
	public String handleRegistrationRequest(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "register";
	}
	
	@PostMapping(value= {"/register"})
	public String registerNewUser(@Valid UserForm userForm, BindingResult binding) {
		if (binding.hasErrors()) {
			return "register";
		}
		if (!userForm.getPassword().equals(userForm.getPassword2())) {
			binding.addError(new FieldError("userForm", "password2", "Passwords must match"));
			return "register";
		}
		Role newRole = new Role(userForm.getEmail(), "ROLE_USER");
		newRole = roleService.addRole(newRole);
		if (newRole == null) {
			binding.addError(new FieldError("userForm", "email", "User already exists with USER role"));
			return "register";
		}
		User newUser = new User(userForm.getEmail(), passEnc.encode(userForm.getPassword()), userForm.getName(), userForm.getSurname(), userForm.getPhoneNumber(), newRole, true);
		newUser = userService.addUser(newUser);
		if (newUser == null) {
			binding.addError(new FieldError("userForm", "email", "User already exists"));
			return "register";
		}
		return "redirect:/user/" + newUser.getUserId();
	}
	
	@GetMapping(value= {"/login"})
	public String handleLoginRequest() {
		return "login";
	}

}

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ie.domis.domain.Bid;
import ie.domis.domain.Job;
import ie.domis.domain.Role;
import ie.domis.domain.User;
import ie.domis.forms.BidForm;
import ie.domis.forms.JobForm;
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
		if (job == null) {
			model.addAttribute("id", id);
			return "doesnotexist";
		}
		List<Bid> bids = bidService.findAllBidsByJob(id);
		model.addAttribute("job", job);
		model.addAttribute("bids", bids);
		if (!job.isActive() || (loggedInUser.getUserId() == job.getOwner().getUserId())) {
			model.addAttribute("canBid", false);
		} else {
			model.addAttribute("canBid", true);
		}
		BidForm bidForm = new BidForm();
		bidForm.setBidderId(loggedInUser.getUserId());
		bidForm.setJobId(id);
		model.addAttribute("bidForm", bidForm);
		return "job";
	}
	
	@PostMapping(value= {"/newbid"})
	public String handleNewBidRequest(@Valid BidForm bidForm, BindingResult binding, RedirectAttributes redirectAttributes) {
		if (binding.hasErrors()) {
			redirectAttributes.addFlashAttribute("valueError", true);
			return "redirect:/job/" + bidForm.getJobId();
		}
		User user = userService.findById(bidForm.getBidderId());
		if (user == null) {
			return "redirect:/job/" + bidForm.getJobId();
		}
		Job job = jobService.findJobById(bidForm.getJobId());
		if (job == null) {
			return "redirect:/job/" + bidForm.getJobId();
		}
		Bid newBid = new Bid(user, job, bidForm.getValue());
		newBid = bidService.addBid(newBid);
		if (newBid == null) {
			redirectAttributes.addFlashAttribute("valueError", true);
			return "redirect:/job/" + bidForm.getJobId();
		}
		return "redirect:/job/" + bidForm.getJobId();
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
		JobForm jobForm = new JobForm();
		jobForm.setOwnerId(loggedInUser.getUserId());
		model.addAttribute("jobForm", jobForm);
		return "account";
	}

	@PostMapping(value= {"/newjob"})
	public String handleNewJobRequest(@Valid JobForm jobForm, BindingResult binding, RedirectAttributes redirectAttributes) {
		if (binding.hasErrors()) {
			redirectAttributes.addFlashAttribute("valueError", true);
			return "redirect:/user/" + jobForm.getOwnerId();
		}
		User user = userService.findById(jobForm.getOwnerId());
		if (user == null) {
			return "redirect:/user/" + jobForm.getOwnerId();
		}
		Job newJob = new Job(jobForm.getName(), jobForm.getDescription(), user);
		newJob = jobService.addJob(newJob);
		if (newJob == null) {
			redirectAttributes.addFlashAttribute("valueError", true);
			return "redirect:/user/" + jobForm.getOwnerId();
		}
		return "redirect:/job/" + newJob.getJobId();
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

package ie.domis.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ie.domis.domain.Bid;
import ie.domis.domain.Job;
import ie.domis.domain.User;
import ie.domis.forms.BidForm;
import ie.domis.forms.JobForm;
import ie.domis.service.BidService;
import ie.domis.service.JobService;
import ie.domis.service.UserService;

@Controller
public class JobController {

	@Autowired
	JobService jobService;
	
	@Autowired
	BidService bidService;
	
	@Autowired
	UserService userService;
	
	@GetMapping(value= {"/job/{id}"})
	public String handleJobRequest(@PathVariable("id") int id, Model model, Principal user) {
		User loggedInUser = userService.findByEmail(user.getName());
		model.addAttribute("user", loggedInUser);
		Job job = jobService.findJobById(id);
		if (job == null) {
			model.addAttribute("id", id);
			return "doesnotexist";
		}
		model.addAttribute("job", job);
		if (job.isActive()) {
			List<Bid> bids = bidService.findAllBidsByJob(id);
			model.addAttribute("bids", bids);
		} else {
			Bid winningBid = bidService.getMinBidForJob(id);
			if (winningBid != null) {
				model.addAttribute("winningBid", winningBid);
			}
		}
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
	
}

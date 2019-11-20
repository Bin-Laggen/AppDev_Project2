package ie.domis.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ie.domis.domain.Bid;
import ie.domis.domain.Job;
import ie.domis.domain.User;
import ie.domis.forms.BidForm;
import ie.domis.service.BidService;
import ie.domis.service.JobService;
import ie.domis.service.UserService;

@Controller
public class BidController {

	@Autowired
	JobService jobService;
	
	@Autowired
	BidService bidService;
	
	@Autowired
	UserService userService;

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
	
}

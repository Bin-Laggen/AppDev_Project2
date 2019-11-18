package ie.domis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ie.domis.domain.Bid;
import ie.domis.domain.Job;
import ie.domis.service.BidService;
import ie.domis.service.JobService;

@Controller
public class MainController {
	
	@Autowired
	JobService jobService;
	
	@Autowired
	BidService bidService;
	
	@Secured({"ROLE_USER"})//"ROLE_ADMIN", 
	@GetMapping(value= {"/", "/index"})
	public String handleIndexRequest(Model model) {
		List<Job> jobs = jobService.findAllJobs();
		model.addAttribute("jobs", jobs);
		return "index";
	}
	
	@Secured({"ROLE_USER"})//"ROLE_ADMIN", 
	@GetMapping(value= {"/job/{id}"})
	public String handleJobRequest(@PathVariable("id") int id, Model model) {
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

}

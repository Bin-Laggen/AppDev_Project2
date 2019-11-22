package ie.domis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ie.domis.domain.Bid;
import ie.domis.domain.Job;
import ie.domis.domain.User;
import ie.domis.service.BidService;
import ie.domis.service.JobService;
import ie.domis.service.UserService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

	@Autowired
	JobService jobService;
	
	@Autowired
	BidService bidService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/jobs")
	public List<Job> activeJobs() {
		return jobService.findAllActiveJobs();
	}
	
	@GetMapping("/bids/{userId}")
	public List<Bid> usersBids(@PathVariable("userId") int userId) {
		return bidService.findAllBidsByBidder(userId);
	}

	@GetMapping("/users")
	public List<User> users() {
		return userService.findAll();
	}
	
}

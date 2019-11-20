package ie.domis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import ie.domis.dao.RoleDAO;
import ie.domis.domain.Bid;
import ie.domis.domain.Job;
import ie.domis.domain.User;
import ie.domis.service.BidService;
import ie.domis.service.JobService;
import ie.domis.service.UserService;

@SpringBootApplication
@EnableScheduling
public class AppdevProject2Application implements CommandLineRunner {

	@Autowired
	UserService userService;
	
	@Autowired
	JobService jobService;
	
	@Autowired
	RoleDAO roleDao;
	
	@Autowired
	BidService bidService;

	public static void main(String[] args) {
		SpringApplication.run(AppdevProject2Application.class, args);
	}
	

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World\n");
		
//		User admin = userService.findByEmail("admin@email.com");
//		if (admin != null) {
//			System.out.println(admin);
//			Bid b1 = bidService.findById(1);
//			if (b1 != null) {
//				System.out.println(bidService.updateBidBidder(b1.getBidId(), admin));
//			}
//		}
//
//		System.out.println("\n\n========== USERS ==========");
//		List<Integer> userIds = userService.findAllUserIds();
//		System.out.println("IDs: " + userIds.toString());
//
//		System.out.println("\nALL USERS");
//		List<User> users = userService.findAll();
//		for (User u : users) {
//			System.out.println(u);
//		}
//		
//		System.out.println("\nALL EMAILS");
//		List<String> userEmails = userService.findAllUserEmails();
//		for (String s : userEmails) {
//			System.out.println(s);
//		}
//		System.out.println();
//
//		System.out.println("\nALL ACTIVE USERS");
//		List<User> activeUsers = userService.findAllActiveUsers();
//		for (User u : activeUsers) {
//			System.out.println(u);
//		}
//
//		System.out.println("\n\n========== JOBS ==========");
//		List<Integer> jobIds = jobService.findAllJobIds();
//		System.out.println("IDs: " + jobIds.toString());
//
//		System.out.println("\nALL JOBS");
//		List<Job> jobs = jobService.findAllJobs();
//		for (Job j : jobs) {
//			System.out.println(j);
//		}
//		
//		System.out.println("\nUSER 2 JOBS");
//		List<Job> user1Jobs = jobService.findAllOwnersJobs(2);
//		for (Job j : user1Jobs) {
//			System.out.println(j);
//		}
//		
//		System.out.println("\nACTIVE JOBS");
//		List<Job> activeJobs = jobService.findAllActiveJobs();
//		for (Job j : activeJobs) {
//			System.out.println(j);
//		}
//		
//		System.out.println("\n\n========== BIDS ==========");
//		List<Integer> bidIds = bidService.findAllBidIds();
//		System.out.println("IDs: " + bidIds.toString());
//
//		System.out.println("\nALL BIDS");
//		List<Bid> bids = bidService.findAll();
//		for (Bid b : bids) {
//			System.out.println(b);
//		}
//		
//		System.out.println("\nUSER 1 BIDS");
//		List<Bid> user1Bids = bidService.findAllBidsByBidder(1);
//		for (Bid b : user1Bids) {
//			System.out.println(b);
//		}
//		
//		System.out.println("\nJOB 1 BIDS");
//		List<Bid> job1Bids = bidService.findAllBidsByJob(1);
//		for (Bid b : job1Bids) {
//			System.out.println(b);
//		}
	}

}

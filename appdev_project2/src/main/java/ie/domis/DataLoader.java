package ie.domis;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ie.domis.dao.RoleDAO;
import ie.domis.domain.Bid;
import ie.domis.domain.Job;
import ie.domis.domain.Role;
import ie.domis.domain.User;
import ie.domis.service.BidService;
import ie.domis.service.JobService;
import ie.domis.service.UserService;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	UserService userService;
	
	@Autowired
	JobService jobService;
	
	@Autowired
	RoleDAO roleDao;
	
	@Autowired
	BidService bidService;
	
	@Autowired
	PasswordEncoder passEnc;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Role johnRole = new Role("john@email.com", "ROLE_USER");
		roleDao.save(johnRole);
		Role maryRole = new Role("mary@email.com", "ROLE_USER");
		roleDao.save(maryRole);
		Role patRole = new Role("pat@email.com", "ROLE_USER");
		roleDao.save(patRole);
		Role adminRole = new Role("admin@email.com", "ROLE_ADMIN");
		roleDao.save(adminRole);
		
		User john = new User("john@email.com", passEnc.encode("johnPass"), "John", "Johnson", 871234567, johnRole, true);
		userService.addUser(john);
		User mary = new User("mary@email.com", passEnc.encode("maryPass"), "Mary", "Cook", 857654321, maryRole, true);
		userService.addUser(mary);
		User pat = new User("pat@email.com", passEnc.encode("patPass"), "Pat", "Murphy", 865431267, patRole, true);
		userService.addUser(pat);
		User admin = new User("admin@email.com", passEnc.encode("adminPass"), "Admin", "Admin", 123456789, adminRole, true);
		userService.addUser(admin);
		
		Job j1 = new Job("Bathroom refurbish", "Small ensuite bathroom needs new tiles", LocalDateTime.now().minusDays(21), true, mary);
		jobService.addJob(j1);
		Job j2 = new Job("Bedroom repaint", "Bedroom repaint required after dog left mud on the walls", LocalDateTime.now(), true, mary);
		jobService.addJob(j2);
		Job j3 = new Job("IKEA kitchen install", "IKEA kitchen install", LocalDateTime.now(), true, john);
		jobService.addJob(j3);
		Job j4 = new Job("Garage cleanup", "Remove junk and old shelving", LocalDateTime.now(), true, pat);
		jobService.addJob(j4);
		
		Bid b1 = new Bid(pat, j2, 150);
		b1 = bidService.addBid(b1);
		
		Bid b2 = new Bid(john, j2, 125);
		b2 = bidService.addBid(b2);
		
		Bid b3 = new Bid(john, j1, 500);
		b3 = bidService.addBid(b3);

		Bid b4 = new Bid(pat, j2, 100);
		b4 = bidService.addBid(b4);
		
		Bid b5 = new Bid(mary, j3, 1000);
		b5 = bidService.addBid(b5);
		
		Bid b6 = new Bid(pat, j1, 450);
		b6 = bidService.addBid(b6);
		
		
		
//		System.out.println(john);
//		System.out.println(mary);
//		System.out.println(pat);
//		System.out.println(admin);
//		
//		System.out.println("\n===================================\n");
	}

}

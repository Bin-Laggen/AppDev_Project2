package ie.domis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ie.domis.dao.JobDAO;
import ie.domis.dao.RoleDAO;
import ie.domis.dao.UserDAO;
import ie.domis.domain.Bid;
import ie.domis.domain.User;
import ie.domis.service.BidService;

@SpringBootApplication
public class AppdevProject2Application implements CommandLineRunner {

	@Autowired
	UserDAO userDao;
	
	@Autowired
	JobDAO jobDao;
	
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
		
		User admin = userDao.findByEmail("admin@email.com");
		if (admin != null) {
			System.out.println(admin);
			Bid b1 = bidService.findById(1);
			if (b1 != null) {
				System.out.println(bidService.updateBidBidder(b1.getBidId(), admin));
			}
		}
	}

}

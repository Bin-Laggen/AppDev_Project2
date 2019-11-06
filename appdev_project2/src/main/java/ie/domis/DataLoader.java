package ie.domis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ie.domis.dao.JobDAO;
import ie.domis.dao.RoleDAO;
import ie.domis.dao.UserDAO;
import ie.domis.domain.Role;
import ie.domis.domain.User;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	UserDAO userDao;
	
	@Autowired
	JobDAO jobDao;
	
	@Autowired
	RoleDAO roleDao;
	
	@Autowired
	PasswordEncoder passEnc;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Role johnRole = roleDao.save(new Role("john@email.com", "ROLE_USER"));
		Role maryRole = roleDao.save(new Role("mary@email.com", "ROLE_USER"));
		Role patRole = roleDao.save(new Role("pat@email.com", "ROLE_USER"));
		Role adminRole = roleDao.save(new Role("admin@email.com", "ROLE_ADMIN"));
		
		User john = new User("john@email.com", passEnc.encode("johnPass"), "John", "Johnson", 871234567, johnRole, true);
		userDao.save(john);
		User mary = new User("mary@email.com", passEnc.encode("maryPass"), "Mary", "Cook", 857654321, maryRole, true);
		userDao.save(mary);
		User pat = new User("pat@email.com", passEnc.encode("patPass"), "Pat", "Murphy", 865431267, patRole, true);
		userDao.save(pat);
		User admin = new User("admin@email.com", passEnc.encode("adminPass"), "Admin", "Admin", 123456789, adminRole, true);
		userDao.save(admin);
		
//		System.out.println(john);
//		System.out.println(mary);
//		System.out.println(pat);
//		System.out.println(admin);
//		
//		System.out.println("\n===================================\n");
	}

}

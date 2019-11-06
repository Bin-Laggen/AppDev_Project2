package ie.domis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import ie.domis.dao.JobDAO;
import ie.domis.dao.RoleDAO;
import ie.domis.dao.UserDAO;
import ie.domis.domain.Role;
import ie.domis.domain.User;

@SpringBootApplication
public class AppdevProject2Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AppdevProject2Application.class, args);
	}
	

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World\n");
		
		
		
		
	}

}

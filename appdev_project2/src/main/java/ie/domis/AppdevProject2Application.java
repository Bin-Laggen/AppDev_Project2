package ie.domis;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

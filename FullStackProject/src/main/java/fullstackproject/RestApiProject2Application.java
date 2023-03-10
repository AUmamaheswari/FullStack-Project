package fullstackproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication   // It includes three configuration--> @EnableAutoConfiguration, @ComponentScan, and @Configuration.
public class RestApiProject2Application {

	public static void main(String[] args) {
		SpringApplication.run(RestApiProject2Application.class, args);
		System.out.println("server started");
	}

}


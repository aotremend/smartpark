package ro.tremend.smartpark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SmartparkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartparkApplication.class, args);
	}
}

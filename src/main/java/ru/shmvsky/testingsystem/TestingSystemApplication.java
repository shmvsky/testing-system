package ru.shmvsky.testingsystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.shmvsky.testingsystem.pojo.RegistrationForm;
import ru.shmvsky.testingsystem.service.UserService;

@SpringBootApplication
public class TestingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestingSystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserService userService) {
		return args -> {
//			RegistrationForm registrationForm = new RegistrationForm("Крупнов Леонид Максимович", "jusy", "pas5");
//            userService.register(registrationForm);
        };
	}

}

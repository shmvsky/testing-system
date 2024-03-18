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
//            RegistrationForm regForm1 = RegistrationForm.builder()
//                    .fullname("a")
//                    .username("a")
//                    .password("pas1")
//                    .build();
//            userService.register(regForm1);
//
//			RegistrationForm regForm2 = RegistrationForm.builder()
//					.fullname("b")
//					.username("b")
//					.password("pas2")
//					.build();
//			userService.register(regForm2);
//
//			RegistrationForm regForm3 = RegistrationForm.builder()
//					.fullname("c")
//					.username("c")
//					.password("pas3")
//					.build();
//			userService.register(regForm3);
//
//			RegistrationForm regForm4 = RegistrationForm.builder()
//					.fullname("d")
//					.username("d")
//					.password("pas4")
//					.build();
//			userService.register(regForm4);
        };
	}

}

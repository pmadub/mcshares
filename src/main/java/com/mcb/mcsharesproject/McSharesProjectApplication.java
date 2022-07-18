package com.mcb.mcsharesproject;

import com.mcb.mcsharesproject.entities.user.User;
import com.mcb.mcsharesproject.services.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class McSharesProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(McSharesProjectApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			User user = new User();
			user.setUserName("admin");
			user.setName("ADMIN");
			user.setPassword("1234");
			userService.saveUser(user);
		};
	}

}

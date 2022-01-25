package com.skilldistillery.rollthedice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class RollTheDiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RollTheDiceApplication.class, args);
	}
	
	@Bean
	public PasswordEncoder configurePasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

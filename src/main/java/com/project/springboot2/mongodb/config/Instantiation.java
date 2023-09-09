package com.project.springboot2.mongodb.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.project.springboot2.mongodb.domain.User;
import com.project.springboot2.mongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		
		User mariaGarcia = new User(null, "Maria Garcia", "maria-garcia@hotmail.com");
		User jamesJohnson = new User(null, "James Johnson", "james-johnson@gmail.com");
		User georgeDavis = new User(null, "George Davis", "george-davis@gmail.com");
		
		userRepository.saveAll(Arrays.asList(mariaGarcia, jamesJohnson, georgeDavis));
	}
	
}
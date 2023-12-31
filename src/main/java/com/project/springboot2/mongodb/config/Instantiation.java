package com.project.springboot2.mongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.project.springboot2.mongodb.domain.Post;
import com.project.springboot2.mongodb.domain.User;
import com.project.springboot2.mongodb.dto.AuthorDTO;
import com.project.springboot2.mongodb.dto.CommentDTO;
import com.project.springboot2.mongodb.repository.PostRepository;
import com.project.springboot2.mongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User mariaGarcia = new User(null, "Maria Garcia", "maria-garcia@hotmail.com");
		User jamesJohnson = new User(null, "James Johnson", "james-johnson@gmail.com");
		User georgeDavis = new User(null, "George Davis", "george-davis@gmail.com");
		userRepository.saveAll(Arrays.asList(mariaGarcia, jamesJohnson, georgeDavis));
		
		
		Post post1 = new Post(null, sdf.parse("15/03/2017"), "Let's take a trip",
				"We are going to New York!", new AuthorDTO(mariaGarcia));
		Post post2 = new Post(null, sdf.parse("24/09/2019"), "Good morning",
				"I woke up happy today!", new AuthorDTO(mariaGarcia));
		
		CommentDTO comment1 = new CommentDTO("Have a nice trip!", 
				sdf.parse("15/03/2017"), new AuthorDTO(jamesJohnson));
		CommentDTO comment2 = new CommentDTO("Enjoy the trip!", 
				sdf.parse("16/03/2017"), new AuthorDTO(georgeDavis));
		CommentDTO comment3 = new CommentDTO("Have a nice day!",
				sdf.parse("24/09/2019"), new AuthorDTO(jamesJohnson));
		
		post1.getComments().addAll(Arrays.asList(comment1, comment2));
		post2.getComments().add(comment3);
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		mariaGarcia.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(mariaGarcia);
	}
	
}
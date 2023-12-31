package com.project.springboot2.mongodb.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.springboot2.mongodb.domain.Post;
import com.project.springboot2.mongodb.repository.PostRepository;
import com.project.springboot2.mongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;
		
	public Post findById(String id) {
		Optional<Post> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
		
	public List<Post> findByTitle(String title) {
		return repository.searchTitle(title);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 3600 * 1000);
		return repository.fullSearch(text, minDate, maxDate);
	}
	
}
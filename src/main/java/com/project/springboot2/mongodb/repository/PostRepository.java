package com.project.springboot2.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.springboot2.mongodb.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
}
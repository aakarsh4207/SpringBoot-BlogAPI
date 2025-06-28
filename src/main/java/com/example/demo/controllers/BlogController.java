package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Posts;
import com.example.demo.serviceImpl.PostServiceImpl;

@RestController

@RequestMapping("/blog/")

public class BlogController {
	
	@Autowired
	private PostServiceImpl postService;
	
	@PostMapping("/posts")
	public ResponseEntity<Posts> getPost(@RequestBody Posts newPost) {
		
		postService.getPosts(newPost);
		
		return new ResponseEntity<Posts>(newPost, HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("/posts")
	public ResponseEntity<List<Posts>> getPost(@RequestParam String term) {
		
		List<Posts> post = postService.searchPost(term);
		
		return new ResponseEntity<List<Posts>>(post, HttpStatus.OK);
		
	}
	
	
	@PutMapping("/posts/{id}")
	public ResponseEntity<Posts> updatePost(@PathVariable int id, @RequestBody Posts updatePost) {
		
		Posts post = postService.updatePost(id,updatePost);
		
		return new ResponseEntity<Posts>(post, HttpStatus.CREATED);
		
		
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable int id) {
			postService.deletePost(id);
		    return ResponseEntity.noContent().build();
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Posts>>  getAllPosts() {
		List<Posts> postList = postService.getAllPosts();
		return new ResponseEntity<List<Posts>>(postList, HttpStatus.OK);
	}
	
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<Optional<Posts>>  getById(@PathVariable int id) {
		Optional<Posts> postList = postService.getPostById(id);
		return new ResponseEntity<Optional<Posts>>(postList, HttpStatus.OK);
	}
	
	

}

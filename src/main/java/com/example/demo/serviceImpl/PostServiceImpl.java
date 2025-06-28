package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Posts;
import com.example.demo.repositories.PostRepository;

@Service
public class PostServiceImpl {
	
	@Autowired
	private PostRepository postRepository; 
	
	public void getPosts(Posts post) {
		
		postRepository.save(post);
		
	}
	
	public List<Posts> getAllPosts() {
		List<Posts> postList  = postRepository.findAll();
		
		return postList;
	}
	
	public Optional<Posts> getPostById(int id) {
		Optional<Posts> post = postRepository.findById(id);
		
		return post;
	}
	
	public Posts updatePost(int id,Posts updatePost) {
		Posts post = postRepository.findById(id).orElseThrow(()->new RuntimeException("id is not present"+id));
		
		post.setCategory(updatePost.getCategory());
		post.setTitle(updatePost.getTitle());
		post.setContent(updatePost.getContent());
		post.setTags(updatePost.getTags());
		postRepository.save(post);
		return post;
		
		
		
		
	}
	
	
	public void deletePost(int id) {
		postRepository.deleteById(id);
		
	}
	
	public List<Posts> searchPost(String term) {
		List<Posts>post =  postRepository.searchPost(term);
		return post;
		
	}
	

}

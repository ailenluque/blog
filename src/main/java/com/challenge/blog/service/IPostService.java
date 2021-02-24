package com.challenge.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.challenge.blog.model.Posts;

@Service
public interface IPostService {
	void save(Posts posts);

	List<Posts> searchAll();

	Posts searchById(Integer idPosts);

	void delete(Integer idPosts);

	void edit(Integer idPosts);
}

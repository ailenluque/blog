package com.challenge.blog.service;

import java.util.List;
import com.challenge.blog.model.Posts;

public interface IPostService {

	Posts save(Posts posts);

	List<Posts> searchAll();

	Posts searchById(Integer idPost);

	void delete(Integer idPost);

	void edit(Integer idPost);
	
	public String update(Posts post);
}

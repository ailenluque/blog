package com.challenge.blog.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.blog.model.Posts;
import com.challenge.blog.repository.PostRepository;
import com.challenge.blog.service.IPostService;

@Service
public class PostServiceJPA implements IPostService {

	@Autowired
	private PostRepository repoPost;

	@Override
	public Posts save(Posts post) {
		return repoPost.save(post);

	}

	@Override
	public List<Posts> searchAll() {
		return (List<Posts>) repoPost.findAll();
	}

	@Override
	public Posts searchById(Integer idPost) {
		Optional<Posts> optional = repoPost.findById(idPost);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void delete(Integer idPost) {
		repoPost.deleteById(idPost);
	}

	@Override
	public void edit(Integer idPost) {
		repoPost.findById(idPost);
	}

	@Override
	public String update(Posts post) {
		Integer num = post.getId();			
		if (repoPost.findById(num).isPresent()) {		
							
			post.setTitle(post.getTitle());
			post.setCategory(post.getCategory());
			post.setContent(post.getContent());
			post.setDate(post.getDate());
			post.setImage(post.getImage());
			repoPost.save(post);
			return "Modificado";		
			}	
		return "Error al modificar";
	}

}

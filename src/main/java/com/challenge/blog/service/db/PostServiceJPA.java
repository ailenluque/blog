package com.challenge.blog.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.blog.model.Posts;
import com.challenge.blog.repository.PostRepository;
import com.challenge.blog.service.IPostService;

@Service
public class PostServiceJPA implements IPostService {

	@Autowired
	private PostRepository repoPost;
	
	@Transactional
	@Override
	public void save(Posts post) {
		repoPost.save(post);

	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Posts> searchAll() {
		return repoPost.findAll();
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

}

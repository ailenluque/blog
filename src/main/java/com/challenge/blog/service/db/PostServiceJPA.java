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
	public void save(Posts post) {
		repoPost.save(post);

	}
	@Override
	public List<Posts> searchAll() {
		return repoPost.findAll();
	}

	@Override
	public Posts searchById(Integer idPosts) {
		Optional<Posts> optional = repoPost.findById(idPosts);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void delete(Integer idPosts) {
		repoPost.deleteById(idPosts);
	}

	@Override
	public void edit(Integer idPosts) {
		repoPost.findById(idPosts);
	}

}

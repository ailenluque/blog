package com.challenge.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.blog.model.Posts;

@Repository
public interface PostRepository extends JpaRepository<Posts, Integer> {

	Optional<Posts> findById(Integer id);
	Void save(Optional<Posts> postUpdate);
}

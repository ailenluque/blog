package com.challenge.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.blog.model.Posts;

public interface PostRepository extends JpaRepository<Posts, Integer> {

}

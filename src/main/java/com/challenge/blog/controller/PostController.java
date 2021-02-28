package com.challenge.blog.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.challenge.blog.model.Posts;
import com.challenge.blog.service.IPostService;

@RestController
public class PostController {
//new, edit, delete, detail.

	@Autowired
	private IPostService servicePost;

	@GetMapping("/home")
	public String viewPosts(Model model) {
		List<Posts> list = servicePost.searchAll();
		model.addAttribute("posts", list);
		return "home";
	}

	@GetMapping("/detail/{id}")
	public String Detail(@PathVariable int id, Model model) {
		Posts post = servicePost.searchById(id);
		model.addAttribute("post", post);
		return "detail";
	}

	@PostMapping("/home")
	public String save(Posts post) {
		servicePost.save(post);
		return "redirect:home";
	}

	@PatchMapping("/edit/{id}")
	public String edit(@PathVariable("id") int idPost, Model model) {
		Posts post = servicePost.searchById(idPost);
		model.addAttribute("post", post);
		return "new";
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") int idPost) {
		servicePost.delete(idPost);
		return "redirect:/home";
	}
}

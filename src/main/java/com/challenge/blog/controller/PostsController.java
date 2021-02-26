package com.challenge.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.challenge.blog.model.Posts;
import com.challenge.blog.service.IPostService;

@Controller
@SessionAttributes("post")
public class PostsController {
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
	public String save(@Valid Posts post, BindingResult result, RedirectAttributes attributes, SessionStatus status) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
			}
			return "home";
		}
		servicePost.save(post);
		status.setComplete();
		attributes.addFlashAttribute("msg", "fue guardado con exito");
		return "redirect:home";
	}
	

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int idPost, Model model) {
		Posts post = servicePost.searchById(idPost);
		model.addAttribute("post", post);
		return "edit";
	}
	
	@GetMapping("/new")
	public String newPost(Posts post, Model model) {
		model.addAttribute("post", post);
		return "new";
	}
	

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int idPost, Model model, RedirectAttributes attributes) {
		servicePost.delete(idPost);
		attributes.addFlashAttribute("msg", "fue eliminado");
		return "redirect:/home";
	}
}

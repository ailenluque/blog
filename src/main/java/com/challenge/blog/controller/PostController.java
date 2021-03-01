package com.challenge.blog.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.challenge.blog.model.Posts;
import com.challenge.blog.service.IPostService;

@Controller
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

	@PostMapping("/save")
	public String save(@RequestBody Posts post) {
		servicePost.save(post);
		return "redirect:home";
	}
	
	/*@PostMapping("/save")
	Posts post(@RequestBody Posts post) {
	    return servicePost.save(post);
	  }*/

	/*@PatchMapping("/edit/{id}")
	public String edit(@RequestBody Posts post) {
		servicePost.update(post);
		return "redirect:/home";
	}*/
	
	@PatchMapping("/edit/{id}")
    public String edit(@PathVariable int id, @RequestBody Map<String, Object> changes){
        //Fetch the data from the database
        Posts PostUpdate = servicePost.searchById(id);

        //apply the changes to the REST model.
        changes.forEach(
                (change, value) -> {
                    switch (change){
                        case "title": PostUpdate.setTitle((String) value); break;
                        case "category": PostUpdate.setCategory((String) value); break;
                        case "content": PostUpdate.setContent((String) value); break;
                        case "date": PostUpdate.setDate((LocalDateTime) value); break;
                        case "image": PostUpdate.setImage((String) value); break;
                        /*case "description": ticketRestModel.setDescription((String) value); break;
                        case "status": ticketRestModel.setStatus((String) value); break;*/
                    }
                }
        );
        servicePost.update(PostUpdate);
        return "redirect:/home";
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") int idPost) {
		servicePost.delete(idPost);
		return "redirect:/home";
	}
}

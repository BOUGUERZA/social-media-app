package com.socialmedia.app.controller;

import com.socialmedia.app.model.Post;
import com.socialmedia.app.model.User;
import com.socialmedia.app.service.PostService;
import com.socialmedia.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "post/create";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute Post post, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        if (currentUser != null) {
            post.setUser(currentUser);
            postService.createPost(post);
            redirectAttributes.addFlashAttribute("success", "Post created successfully");
            return "redirect:/home";
        }
        
        redirectAttributes.addFlashAttribute("error", "Failed to create post");
        return "redirect:/posts/create";
    }

    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        Post post = postService.findById(id).orElse(null);
        
        if (post != null) {
            model.addAttribute("post", post);
            model.addAttribute("currentUser", currentUser);
            return "post/view";
        }
        
        return "redirect:/home";
    }

    @GetMapping("/{id}/edit")
    public String editPostForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        Post post = postService.findById(id).orElse(null);
        
        if (post != null && post.getUser().getId().equals(currentUser.getId())) {
            model.addAttribute("post", post);
            return "post/edit";
        }
        
        redirectAttributes.addFlashAttribute("error", "You can only edit your own posts");
        return "redirect:/home";
    }

    @PostMapping("/{id}/edit")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post updatedPost, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        Post post = postService.findById(id).orElse(null);
        
        if (post != null && post.getUser().getId().equals(currentUser.getId())) {
            post.setContent(updatedPost.getContent());
            post.setImageUrl(updatedPost.getImageUrl());
            postService.updatePost(post);
            redirectAttributes.addFlashAttribute("success", "Post updated successfully");
            return "redirect:/posts/" + id;
        }
        
        redirectAttributes.addFlashAttribute("error", "Failed to update post");
        return "redirect:/home";
    }

    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        Post post = postService.findById(id).orElse(null);
        
        if (post != null && post.getUser().getId().equals(currentUser.getId())) {
            postService.deletePost(post);
            redirectAttributes.addFlashAttribute("success", "Post deleted successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "You can only delete your own posts");
        }
        
        return "redirect:/home";
    }
}

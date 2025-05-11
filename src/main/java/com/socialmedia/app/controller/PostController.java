package com.socialmedia.app.controller;

import com.socialmedia.app.model.Comment;
import com.socialmedia.app.model.Post;
import com.socialmedia.app.model.User;
import com.socialmedia.app.service.CommentService;
import com.socialmedia.app.service.FileStorageService;
import com.socialmedia.app.service.LikeService;
import com.socialmedia.app.service.PostService;
import com.socialmedia.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final FileStorageService fileStorageService;
    private final CommentService commentService;
    private final LikeService likeService;

    @Autowired
    public PostController(PostService postService, UserService userService, 
                         FileStorageService fileStorageService, CommentService commentService,
                         LikeService likeService) {
        this.postService = postService;
        this.userService = userService;
        this.fileStorageService = fileStorageService;
        this.commentService = commentService;
        this.likeService = likeService;
    }

    @GetMapping("/create")
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "post/create";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute Post post, @RequestParam("image") MultipartFile image, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        if (currentUser != null) {
            post.setUser(currentUser);
            
            // Handle the image upload if an image was provided
            if (image != null && !image.isEmpty()) {
                try {
                    String fileName = fileStorageService.storeFile(image);
                    post.setImageUrl("/images/" + fileName);
                } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("error", "Error uploading image: " + e.getMessage());
                    return "redirect:/posts/create";
                }
            }
            
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
        
        if (post != null && currentUser != null) {
            // Get comments for this post
            List<Comment> comments = commentService.findCommentsByPost(post);
            
            // Check if user has liked this post
            boolean hasLiked = likeService.hasUserLikedPost(currentUser, post);
            
            // Add attributes to model
            model.addAttribute("post", post);
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("comments", comments);
            model.addAttribute("newComment", new Comment());
            model.addAttribute("hasLiked", hasLiked);
            
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
    public String updatePost(@PathVariable Long id, @ModelAttribute Post updatedPost, 
                           @RequestParam(value = "image", required = false) MultipartFile image, 
                           RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        Post post = postService.findById(id).orElse(null);
        
        if (post != null && post.getUser().getId().equals(currentUser.getId())) {
            post.setContent(updatedPost.getContent());
            
            // Handle the image upload if a new image was provided
            if (image != null && !image.isEmpty()) {
                try {
                    String fileName = fileStorageService.storeFile(image);
                    post.setImageUrl("/images/" + fileName);
                } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("error", "Error uploading image: " + e.getMessage());
                    return "redirect:/posts/" + id + "/edit";
                }
            } else if (updatedPost.getImageUrl() != null) {
                // Keep the existing image URL if provided
                post.setImageUrl(updatedPost.getImageUrl());
            }
            
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
    
    @PostMapping("/{id}/like")
    public String likePost(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        Post post = postService.findById(id).orElse(null);
        
        if (currentUser != null && post != null) {
            likeService.likePost(currentUser, post);
            return "redirect:/posts/" + id;
        }
        
        redirectAttributes.addFlashAttribute("error", "Impossible d'aimer ce post");
        return "redirect:/home";
    }

    @PostMapping("/{id}/unlike")
    public String unlikePost(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        Post post = postService.findById(id).orElse(null);
        
        if (currentUser != null && post != null) {
            likeService.unlikePost(currentUser, post);
            return "redirect:/posts/" + id;
        }
        
        redirectAttributes.addFlashAttribute("error", "Impossible de ne plus aimer ce post");
        return "redirect:/home";
    }
}

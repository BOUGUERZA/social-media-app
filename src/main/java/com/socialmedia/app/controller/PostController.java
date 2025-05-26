package com.socialmedia.app.controller;

import com.socialmedia.app.model.Post;
import com.socialmedia.app.model.User;
import com.socialmedia.app.service.FileStorageService;
import com.socialmedia.app.service.LikeService;
import com.socialmedia.app.service.PostService;
import com.socialmedia.app.service.UserService;
import com.socialmedia.app.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final FileStorageService fileStorageService;
    private final LikeService likeService;

    @Autowired
    public PostController(PostService postService, UserService userService, 
                         FileStorageService fileStorageService, LikeService likeService) {
        this.postService = postService;
        this.userService = userService;
        this.fileStorageService = fileStorageService;
        this.likeService = likeService;
    }

    @GetMapping("/create")
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "post/create";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute Post post, @RequestParam("image") MultipartFile image, 
                            RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);

        if (currentUser != null) {
            post.setUser(currentUser);

            // Gérer l'image si elle existe
            if (image != null && !image.isEmpty()) {
                try {
                    String fileName = fileStorageService.storeFile(image);
                    String imageUrl = "/images/" + fileName;
                    post.setImageUrl(imageUrl);
                    
                    // Si le contenu est vide ou contient l'URL de l'image, le vider
                    String content = post.getContent();
                    if (content == null || content.isEmpty() || content.equals(imageUrl) || content.startsWith("/images/")) {
                        post.setContent("");
                    }
                } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("error", "Error uploading image: " + e.getMessage());
                    return "redirect:/posts/create";
                }
            }

            // Sauvegarder le post
            postService.createPost(post);
            redirectAttributes.addFlashAttribute("success", "Post created successfully");
            return "redirect:/home";
        }

        redirectAttributes.addFlashAttribute("error", "Failed to create post");
        return "redirect:/posts/create";
    }

    @PostMapping("/{id}/like")
    public String likePost(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        Post post = postService.findById(id).orElse(null);
        
        if (currentUser != null && post != null) {
            if (likeService.hasUserLikedPost(currentUser, post)) {
                likeService.unlikePost(currentUser, post);
            } else {
                likeService.likePost(currentUser, post);
            }
            return "redirect:/home";
        }
        
        redirectAttributes.addFlashAttribute("error", "Impossible d'aimer ce post");
        return "redirect:/home";
    }

    @PostMapping("/{id}/edit")
    @ResponseBody
    public PostDto editPost(@PathVariable Long id, @RequestParam("content") String content) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        Post post = postService.findById(id).orElse(null);
        
        if (currentUser != null && post != null && post.getUser().getId().equals(currentUser.getId())) {
            post.setContent(content);
            post = postService.updatePost(post);
            return postService.convertToDto(post);
        }
        
        throw new RuntimeException("Impossible de modifier le post");
    }

    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        Post post = postService.findById(id).orElse(null);
        
        if (currentUser != null && post != null && post.getUser().getId().equals(currentUser.getId())) {
            postService.deletePost(post);
            redirectAttributes.addFlashAttribute("success", "Post supprimé avec succès");
        } else {
            redirectAttributes.addFlashAttribute("error", "Impossible de supprimer le post");
        }
        
        return "redirect:/home";
    }

    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        Post post = postService.findById(id).orElse(null);
        
        if (post != null) {
            PostDto postDto = postService.convertToDto(post);
            model.addAttribute("post", postDto);
            model.addAttribute("currentUser", currentUser);
            return "post/view";
        }
        
        return "redirect:/home";
    }

}
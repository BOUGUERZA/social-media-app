package com.socialmedia.app.controller;

import com.socialmedia.app.model.Post;
import com.socialmedia.app.model.User;
import com.socialmedia.app.service.HashtagService;
import com.socialmedia.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/hashtags")
public class HashtagController {

    private final HashtagService hashtagService;
    private final UserService userService;

    @Autowired
    public HashtagController(HashtagService hashtagService, UserService userService) {
        this.hashtagService = hashtagService;
        this.userService = userService;
    }

    @GetMapping("/{name}")
    public String getPostsByHashtag(@PathVariable String name, Model model) {
        List<Post> posts = hashtagService.findPostsByHashtag(name);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        if (currentUser != null) {
            posts.forEach(post -> {
                boolean isLiked = post.getLikes().stream()
                    .anyMatch(like -> like.getUser().getId().equals(currentUser.getId()));
                post.setLikedByUser(isLiked);
            });
        }
        
        model.addAttribute("posts", posts);
        model.addAttribute("hashtag", name);
        model.addAttribute("currentUser", currentUser);
        return "hashtag";
    }

    @GetMapping
    public String getAllHashtags(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        model.addAttribute("hashtags", hashtagService.findAllHashtags());
        model.addAttribute("currentUser", currentUser);
        return "hashtags";
    }
}

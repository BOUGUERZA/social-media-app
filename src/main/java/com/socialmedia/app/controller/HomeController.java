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
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final UserService userService;
    private final PostService postService;

    @Autowired
    public HomeController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/")
    public String index() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        if (currentUser != null) {
            List<Post> feedPosts = postService.findPostsForUserFeed(currentUser);
            model.addAttribute("posts", feedPosts);
            model.addAttribute("currentUser", currentUser);
            return "home";
        }
        
        return "redirect:/login";
    }

    @GetMapping("/explore")
    public String explore(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        List<Post> allPosts = postService.findAllPosts();
        model.addAttribute("posts", allPosts);
        model.addAttribute("currentUser", currentUser);
        
        return "explore";
    }
}

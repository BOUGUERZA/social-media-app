package com.socialmedia.app.controller;

import com.socialmedia.app.dto.PostDto;
import com.socialmedia.app.model.Post;
import com.socialmedia.app.model.User;
import com.socialmedia.app.model.Hashtag;
import com.socialmedia.app.service.PostService;
import com.socialmedia.app.service.UserService;
import com.socialmedia.app.service.HashtagService;
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
    private final HashtagService hashtagService;

    @Autowired
    public HomeController(UserService userService, PostService postService, HashtagService hashtagService) {
        this.userService = userService;
        this.postService = postService;
        this.hashtagService = hashtagService;
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
            List<PostDto> feedPosts = postService.getFeedPostsForUser(currentUser);
            List<User> suggestions = userService.findUserSuggestions(currentUser);
            List<Hashtag> trendingHashtags = hashtagService.findTrendingHashtags(4);
            
            model.addAttribute("posts", feedPosts);
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("suggestions", suggestions);
            model.addAttribute("trendingHashtags", trendingHashtags);
            return "acceuil";
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

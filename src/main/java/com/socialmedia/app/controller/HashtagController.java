package com.socialmedia.app.controller;

import com.socialmedia.app.model.Post;
import com.socialmedia.app.service.HashtagService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public HashtagController(HashtagService hashtagService) {
        this.hashtagService = hashtagService;
    }

    @GetMapping("/{name}")
    public String getPostsByHashtag(@PathVariable String name, Model model) {
        List<Post> posts = hashtagService.findPostsByHashtag(name);
        model.addAttribute("posts", posts);
        model.addAttribute("hashtag", name);
        return "hashtag";
    }

    @GetMapping
    public String getAllHashtags(Model model) {
        model.addAttribute("hashtags", hashtagService.findAllHashtags());
        return "hashtags";
    }
}

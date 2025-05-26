package com.socialmedia.app.controller;

import com.socialmedia.app.dto.SearchPostDto;
import com.socialmedia.app.dto.SearchUserDto;
import com.socialmedia.app.model.Post;
import com.socialmedia.app.model.User;
import com.socialmedia.app.service.PostService;
import com.socialmedia.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final UserService userService;
    private final PostService postService;

    @Autowired
    public SearchController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Map<String, Object>> search(@RequestParam String query) {
        try {
            Map<String, Object> results = new HashMap<>();
            
            // Recherche d'utilisateurs
            List<User> users = userService.searchUsersByUsernameOrDisplayName(query);
            List<SearchUserDto> userDtos = new ArrayList<>();
            for (User user : users) {
                userDtos.add(new SearchUserDto(
                    user.getUsername(),
                    user.getDisplayName(),
                    user.getProfileImageUrl()
                ));
            }
            results.put("users", userDtos);
            
            // Recherche de posts
            List<Post> posts = postService.searchPostsByKeyword(query);
            List<SearchPostDto> postDtos = new ArrayList<>();
            for (Post post : posts) {
                User postUser = post.getUser();
                SearchUserDto userDto = new SearchUserDto(
                    postUser.getUsername(),
                    postUser.getDisplayName(),
                    postUser.getProfileImageUrl()
                );
                postDtos.add(new SearchPostDto(
                    post.getId(),
                    post.getContent(),
                    userDto
                ));
            }
            results.put("posts", postDtos);
            
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Une erreur est survenue lors de la recherche");
            return ResponseEntity.badRequest().body(error);
        }
    }
} 
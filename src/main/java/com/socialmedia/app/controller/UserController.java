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

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PostService postService;

    @Autowired
    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/{username}")
    public String viewProfile(@PathVariable String username, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        User profileUser = userService.findByUsername(username).orElse(null);
        
        if (profileUser != null) {
            List<Post> userPosts = postService.findPostsByUser(profileUser);
            
            model.addAttribute("profileUser", profileUser);
            model.addAttribute("posts", userPosts);
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("isFollowing", currentUser != null && currentUser.getFollowing().contains(profileUser));
            
            return "user/profile";
        }
        
        return "redirect:/home";
    }

    @GetMapping("/profile/edit")
    public String editProfileForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        if (currentUser != null) {
            model.addAttribute("user", currentUser);
            return "user/edit";
        }
        
        return "redirect:/home";
    }

    @PostMapping("/profile/edit")
    public String updateProfile(@ModelAttribute User updatedUser, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        if (currentUser != null) {
            currentUser.setDisplayName(updatedUser.getDisplayName());
            currentUser.setBio(updatedUser.getBio());
            currentUser.setProfileImageUrl(updatedUser.getProfileImageUrl());
            
            userService.updateProfile(currentUser);
            redirectAttributes.addFlashAttribute("success", "Profile updated successfully");
            return "redirect:/users/" + currentUser.getUsername();
        }
        
        redirectAttributes.addFlashAttribute("error", "Failed to update profile");
        return "redirect:/users/profile/edit";
    }

    @PostMapping("/{username}/follow")
    public String followUser(@PathVariable String username, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        User userToFollow = userService.findByUsername(username).orElse(null);
        
        if (currentUser != null && userToFollow != null && !currentUser.getId().equals(userToFollow.getId())) {
            userService.followUser(currentUser, userToFollow);
            redirectAttributes.addFlashAttribute("success", "You are now following " + username);
        }
        
        return "redirect:/users/" + username;
    }

    @PostMapping("/{username}/unfollow")
    public String unfollowUser(@PathVariable String username, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        User userToUnfollow = userService.findByUsername(username).orElse(null);
        
        if (currentUser != null && userToUnfollow != null) {
            userService.unfollowUser(currentUser, userToUnfollow);
            redirectAttributes.addFlashAttribute("success", "You have unfollowed " + username);
        }
        
        return "redirect:/users/" + username;
    }

    @GetMapping("/followers")
    public String viewFollowers(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        if (currentUser != null) {
            model.addAttribute("users", currentUser.getFollowers());
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("listType", "Followers");
            return "user/list";
        }
        
        return "redirect:/home";
    }

    @GetMapping("/following")
    public String viewFollowing(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        if (currentUser != null) {
            model.addAttribute("users", currentUser.getFollowing());
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("listType", "Following");
            return "user/list";
        }
        
        return "redirect:/home";
    }
}

package com.socialmedia.app.controller;

import com.socialmedia.app.model.Comment;
import com.socialmedia.app.model.Post;
import com.socialmedia.app.model.User;
import com.socialmedia.app.service.CommentService;
import com.socialmedia.app.service.LikeService;
import com.socialmedia.app.service.PostService;
import com.socialmedia.app.service.UserService;
import com.socialmedia.app.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;
    private final UserService userService;
    private final LikeService likeService;

    @Autowired
    public CommentController(CommentService commentService, PostService postService, 
                            UserService userService, LikeService likeService) {
        this.commentService = commentService;
        this.postService = postService;
        this.userService = userService;
        this.likeService = likeService;
    }

    @PostMapping("/create")
    @ResponseBody
    public CommentDto createComment(@RequestParam("postId") Long postId, 
                               @RequestParam("content") String content) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        Post post = postService.findById(postId).orElse(null);
        
        if (currentUser != null && post != null && content != null && !content.trim().isEmpty()) {
            Comment comment = new Comment();
            comment.setContent(content);
            comment.setUser(currentUser);
            comment.setPost(post);
            
            comment = commentService.createComment(comment);
            
            // Convertir le commentaire en DTO
            CommentDto commentDto = new CommentDto();
            commentDto.setId(comment.getId());
            commentDto.setContent(comment.getContent());
            commentDto.setCreatedAt(comment.getCreatedAt());
            commentDto.setUserName(currentUser.getDisplayName() != null ? 
                currentUser.getDisplayName() : currentUser.getUsername());
            commentDto.setUserAvatar(currentUser.getProfileImageUrl());
            commentDto.setLikes(0); // Nouveau commentaire, donc 0 likes
            
            return commentDto;
        }
        
        throw new RuntimeException("Impossible d'ajouter le commentaire");
    }

    @PostMapping("/{id}/delete")
    public String deleteComment(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        Comment comment = commentService.findById(id).orElse(null);
        
        if (comment != null && currentUser != null) {
            // Check if the user is the comment owner or the post owner
            boolean isCommentOwner = comment.getUser().getId().equals(currentUser.getId());
            boolean isPostOwner = comment.getPost().getUser().getId().equals(currentUser.getId());
            
            if (isCommentOwner || isPostOwner) {
                Long postId = comment.getPost().getId();
                commentService.deleteComment(comment);
                redirectAttributes.addFlashAttribute("success", "Commentaire supprimé avec succès");
                return "redirect:/posts/" + postId;
            } else {
                redirectAttributes.addFlashAttribute("error", "Vous n'êtes pas autorisé à supprimer ce commentaire");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Commentaire introuvable");
        }
        
        return "redirect:/home";
    }

    @PostMapping("/{id}/like")
    @ResponseBody
    public CommentDto likeComment(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        Comment comment = commentService.findById(id).orElse(null);
        
        if (currentUser != null && comment != null) {
            if (likeService.hasUserLikedComment(currentUser, comment)) {
                likeService.unlikeComment(currentUser, comment);
            } else {
                likeService.likeComment(currentUser, comment);
            }
            
            // Convertir le commentaire en DTO
            CommentDto commentDto = new CommentDto();
            commentDto.setId(comment.getId());
            commentDto.setContent(comment.getContent());
            commentDto.setCreatedAt(comment.getCreatedAt());
            commentDto.setUserName(comment.getUser().getDisplayName() != null ? 
                comment.getUser().getDisplayName() : comment.getUser().getUsername());
            commentDto.setUserAvatar(comment.getUser().getProfileImageUrl());
            commentDto.setLikes(likeService.countLikesByComment(comment));
            
            return commentDto;
        }
        
        throw new RuntimeException("Impossible d'aimer ce commentaire");
    }

    @PostMapping("/{id}/unlike")
    public String unlikeComment(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        Comment comment = commentService.findById(id).orElse(null);
        
        if (currentUser != null && comment != null) {
            likeService.unlikeComment(currentUser, comment);
            return "redirect:/posts/" + comment.getPost().getId();
        }
        
        redirectAttributes.addFlashAttribute("error", "Impossible de ne plus aimer ce commentaire");
        return "redirect:/home";
    }
}

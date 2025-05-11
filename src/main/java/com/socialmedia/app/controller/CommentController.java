package com.socialmedia.app.controller;

import com.socialmedia.app.model.Comment;
import com.socialmedia.app.model.Post;
import com.socialmedia.app.model.User;
import com.socialmedia.app.service.CommentService;
import com.socialmedia.app.service.LikeService;
import com.socialmedia.app.service.PostService;
import com.socialmedia.app.service.UserService;
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
    public String createComment(@RequestParam("postId") Long postId, 
                               @RequestParam("content") String content,
                               RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        Post post = postService.findById(postId).orElse(null);
        
        if (currentUser != null && post != null && content != null && !content.trim().isEmpty()) {
            Comment comment = new Comment();
            comment.setContent(content);
            comment.setUser(currentUser);
            comment.setPost(post);
            
            commentService.createComment(comment);
            redirectAttributes.addFlashAttribute("success", "Commentaire ajouté avec succès");
        } else {
            redirectAttributes.addFlashAttribute("error", "Impossible d'ajouter le commentaire");
        }
        
        return "redirect:/posts/" + postId;
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
    public String likeComment(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName()).orElse(null);
        
        Comment comment = commentService.findById(id).orElse(null);
        
        if (currentUser != null && comment != null) {
            likeService.likeComment(currentUser, comment);
            return "redirect:/posts/" + comment.getPost().getId();
        }
        
        redirectAttributes.addFlashAttribute("error", "Impossible d'aimer ce commentaire");
        return "redirect:/home";
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

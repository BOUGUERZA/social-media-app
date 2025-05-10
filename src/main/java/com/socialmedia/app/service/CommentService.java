package com.socialmedia.app.service;

import com.socialmedia.app.model.Comment;
import com.socialmedia.app.model.Post;
import com.socialmedia.app.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    public List<Comment> findCommentsByPost(Post post) {
        return commentRepository.findByPostOrderByCreatedAtDesc(post);
    }

    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }
}

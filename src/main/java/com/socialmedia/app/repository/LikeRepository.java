package com.socialmedia.app.repository;

import com.socialmedia.app.model.Comment;
import com.socialmedia.app.model.Like;
import com.socialmedia.app.model.Post;
import com.socialmedia.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndPost(User user, Post post);
    Optional<Like> findByUserAndComment(User user, Comment comment);
    int countByPost(Post post);
    int countByComment(Comment comment);
    boolean existsByUserAndPost(User user, Post post);
    boolean existsByUserAndComment(User user, Comment comment);
}

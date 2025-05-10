package com.socialmedia.app.repository;

import com.socialmedia.app.model.Like;
import com.socialmedia.app.model.Post;
import com.socialmedia.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndPost(User user, Post post);
    int countByPost(Post post);
    boolean existsByUserAndPost(User user, Post post);
}

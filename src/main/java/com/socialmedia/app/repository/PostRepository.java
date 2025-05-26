package com.socialmedia.app.repository;

import com.socialmedia.app.model.Post;
import com.socialmedia.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserOrderByCreatedAtDesc(User user);
    
    @Query("SELECT p FROM Post p WHERE p.user IN :users ORDER BY p.createdAt DESC")
    List<Post> findPostsByUsersOrderByCreatedAtDesc(List<User> users);
    
    List<Post> findAllByOrderByCreatedAtDesc();
    
    List<Post> findByContentContainingOrderByCreatedAtDesc(String keyword);
}

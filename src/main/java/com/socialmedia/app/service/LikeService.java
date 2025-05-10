package com.socialmedia.app.service;

import com.socialmedia.app.model.Like;
import com.socialmedia.app.model.Post;
import com.socialmedia.app.model.User;
import com.socialmedia.app.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    @Autowired
    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public Like likePost(User user, Post post) {
        if (!likeRepository.existsByUserAndPost(user, post)) {
            Like like = new Like();
            like.setUser(user);
            like.setPost(post);
            return likeRepository.save(like);
        }
        return null;
    }

    public void unlikePost(User user, Post post) {
        Optional<Like> like = likeRepository.findByUserAndPost(user, post);
        like.ifPresent(likeRepository::delete);
    }

    public int countLikesByPost(Post post) {
        return likeRepository.countByPost(post);
    }

    public boolean hasUserLikedPost(User user, Post post) {
        return likeRepository.existsByUserAndPost(user, post);
    }
}

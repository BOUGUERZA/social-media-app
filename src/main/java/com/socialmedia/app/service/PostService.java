package com.socialmedia.app.service;

import com.socialmedia.app.model.Hashtag;
import com.socialmedia.app.model.Post;
import com.socialmedia.app.model.User;
import com.socialmedia.app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final HashtagService hashtagService;

    @Autowired
    public PostService(PostRepository postRepository, HashtagService hashtagService) {
        this.postRepository = postRepository;
        this.hashtagService = hashtagService;
    }

    public Post createPost(Post post) {
        // Process hashtags in the post content
        hashtagService.processHashtagsInPost(post);
        return postRepository.save(post);
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> findPostsByUser(User user) {
        return postRepository.findByUserOrderByCreatedAtDesc(user);
    }

    public List<Post> findPostsForUserFeed(User user) {
        List<User> following = new ArrayList<>(user.getFollowing());
        following.add(user); // Include user's own posts
        return postRepository.findPostsByUsersOrderByCreatedAtDesc(following);
    }

    public List<Post> findAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    public Post updatePost(Post post) {
        // Clear existing hashtags and re-process
        post.getHashtags().clear();
        hashtagService.processHashtagsInPost(post);
        return postRepository.save(post);
    }
    
    public List<Post> findPostsByHashtag(String hashtagName) {
        return hashtagService.findPostsByHashtag(hashtagName);
    }
    
    public List<Post> searchPostsByKeyword(String keyword) {
        return postRepository.findByContentContainingOrderByCreatedAtDesc(keyword);
    }
}

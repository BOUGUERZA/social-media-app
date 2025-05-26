package com.socialmedia.app.service;

import com.socialmedia.app.model.Hashtag;
import com.socialmedia.app.model.Post;
import com.socialmedia.app.repository.HashtagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class HashtagService {

    private final HashtagRepository hashtagRepository;

    @Autowired
    public HashtagService(HashtagRepository hashtagRepository) {
        this.hashtagRepository = hashtagRepository;
    }

    public Hashtag getOrCreateHashtag(String name) {
        // Remove # if present
        if (name.startsWith("#")) {
            name = name.substring(1);
        }
        
        // Convert to lowercase for consistency
        name = name.toLowerCase();
        
        Optional<Hashtag> existingHashtag = hashtagRepository.findByName(name);
        if (existingHashtag.isPresent()) {
            return existingHashtag.get();
        } else {
            Hashtag newHashtag = new Hashtag(name);
            return hashtagRepository.save(newHashtag);
        }
    }

    public List<Hashtag> extractHashtagsFromContent(String content) {
        List<Hashtag> hashtags = new ArrayList<>();
        
        // Pattern to match hashtags
        Pattern pattern = Pattern.compile("#(\\w+)");
        Matcher matcher = pattern.matcher(content);
        
        while (matcher.find()) {
            String hashtagName = matcher.group(1);
            Hashtag hashtag = getOrCreateHashtag(hashtagName);
            hashtags.add(hashtag);
        }
        
        return hashtags;
    }

    public void processHashtagsInPost(Post post) {
        // Extract hashtags from content
        List<Hashtag> hashtags = extractHashtagsFromContent(post.getContent());
        
        // Associate hashtags with post
        for (Hashtag hashtag : hashtags) {
            post.addHashtag(hashtag);
        }
    }

    public List<Post> findPostsByHashtag(String hashtagName) {
        Optional<Hashtag> hashtag = hashtagRepository.findByName(hashtagName.toLowerCase());
        return hashtag.map(value -> new ArrayList<>(value.getPosts())).orElseGet(ArrayList::new);
    }
    
    public List<Hashtag> findAllHashtags() {
        return hashtagRepository.findAll();
    }

    public List<Hashtag> findTrendingHashtags(int limit) {
        List<Hashtag> allHashtags = hashtagRepository.findAll();
        return allHashtags.stream()
            .sorted((h1, h2) -> Integer.compare(h2.getPosts().size(), h1.getPosts().size()))
            .limit(limit)
            .collect(Collectors.toList());
    }
}

package com.socialmedia.app.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hashtags")
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany
    @JoinTable(
        name = "post_hashtags",
        joinColumns = @JoinColumn(name = "hashtag_id"),
        inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private List<Post> posts = new ArrayList<>();
    
    // Constructors
    public Hashtag() {
    }
    
    public Hashtag(String name) {
        this.name = name;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    
    public void addPost(Post post) {
        if (!this.posts.contains(post)) {
            this.posts.add(post);
        }
    }
    
    public void removePost(Post post) {
        this.posts.remove(post);
    }
}

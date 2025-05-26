package com.socialmedia.app.dto;

import java.time.LocalDateTime;

public class CommentDto {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private String userName;
    private String userAvatar;
    private int likes;

    public CommentDto() {
    }

    public CommentDto(Long id, String content, LocalDateTime createdAt, String userName, String userAvatar, int likes) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.likes = likes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
} 
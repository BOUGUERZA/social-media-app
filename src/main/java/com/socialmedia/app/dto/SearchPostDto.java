package com.socialmedia.app.dto;

public class SearchPostDto {
    private Long id;
    private String content;
    private SearchUserDto user;

    public SearchPostDto() {}

    public SearchPostDto(Long id, String content, SearchUserDto user) {
        this.id = id;
        this.content = content;
        this.user = user;
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

    public SearchUserDto getUser() {
        return user;
    }

    public void setUser(SearchUserDto user) {
        this.user = user;
    }
} 
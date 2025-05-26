package com.socialmedia.app.dto;

public class SearchUserDto {
    private String username;
    private String displayName;
    private String profileImageUrl;

    public SearchUserDto() {}

    public SearchUserDto(String username, String displayName, String profileImageUrl) {
        this.username = username;
        this.displayName = displayName;
        this.profileImageUrl = profileImageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
} 
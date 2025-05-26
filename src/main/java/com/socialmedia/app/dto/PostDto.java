package com.socialmedia.app.dto;
import com.socialmedia.app.model.User;
import java.time.LocalDateTime;
import com.socialmedia.app.model.Post;
import java.util.List;

public class PostDto {
    private Long id;
    private String content;
    private String image;
    private LocalDateTime time;
    private String name;
    private String handle;
    private String avatar;
    private int comments;
    private int likes;
    private int retweets;
    private List<CommentDto> commentList;

    // Constructeur vide
    public PostDto() {
    }

    // Constructeur complet
    public PostDto(Long id, String content, String image, LocalDateTime time, String name,
                   String handle, String avatar, int comments, int likes, int retweets, List<CommentDto> commentList) {
        this.id = id;
        this.content = content;
        this.image = image;
        this.time = time;
        this.name = name;
        this.handle = handle;
        this.avatar = avatar;
        this.comments = comments;
        this.likes = likes;
        this.retweets = retweets;
        this.commentList = commentList;
    }

    // Getters et Setters
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getRetweets() {
        return retweets;
    }

    public void setRetweets(int retweets) {
        this.retweets = retweets;
    }

    public List<CommentDto> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentDto> commentList) {
        this.commentList = commentList;
    }

    private PostDto convertToDto(Post post) {
        PostDto dto = new PostDto();

        // Nettoyer le contenu en supprimant les chemins d'images
        String cleanContent = post.getContent().replaceAll("/images/\\S+", "");
        dto.setContent(cleanContent.trim());

        dto.setId(post.getId());
        dto.setImage(post.getImageUrl());
        dto.setTime(post.getCreatedAt());

        User author = post.getUser();
        dto.setName(author.getDisplayName() != null ? author.getDisplayName() : author.getUsername());
        dto.setHandle("@" + author.getUsername());
        dto.setAvatar(author.getProfileImageUrl());

        dto.setComments(post.getComments().size());
        dto.setLikes(post.getLikes().size());
        dto.setRetweets(0); // à adapter si tu ajoutes les retweets

        return dto;
    }

}

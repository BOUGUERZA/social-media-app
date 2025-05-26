package com.socialmedia.app.service;

import com.socialmedia.app.dto.PostDto;
import com.socialmedia.app.dto.CommentDto;
import com.socialmedia.app.model.Hashtag;
import com.socialmedia.app.model.Post;
import com.socialmedia.app.model.User;
import com.socialmedia.app.model.Like;
import com.socialmedia.app.model.Comment;
import com.socialmedia.app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final HashtagService hashtagService;
    private final LikeService likeService;
    private final CommentService commentService;

    @Autowired
    public PostService(PostRepository postRepository, HashtagService hashtagService, LikeService likeService, CommentService commentService) {
        this.postRepository = postRepository;
        this.hashtagService = hashtagService;
        this.likeService = likeService;
        this.commentService = commentService;
    }

    public Post createPost(Post post) {
        // Clean the content before saving to remove any image paths that might be accidentally included
        post.setContent(cleanPostContent(post.getContent(), post.getImageUrl()));
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
        following.add(user);
        return postRepository.findPostsByUsersOrderByCreatedAtDesc(following);
    }

    public List<PostDto> getFeedPostsForUser(User user) {
        List<Post> posts = findPostsForUserFeed(user);
        return posts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PostDto convertToDto(Post post) {
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        
        // Gérer le contenu et l'image
        String content = post.getContent();
        String imageUrl = post.getImageUrl();
        
        // Si le contenu est une URL d'image ou commence par /images/, le vider
        if (content != null && (content.equals(imageUrl) || content.startsWith("/images/"))) {
            content = "";
        }
        
        // Nettoyer le contenu des URLs d'images
        if (content != null) {
            // Supprimer toutes les URLs d'images du contenu
            content = content.replaceAll("(?:/images/[^\\s]+\\.(jpg|jpeg|png|gif|bmp))", "").trim();
            // Supprimer les espaces multiples
            content = content.replaceAll("\\s+", " ").trim();
        }
        
        // Définir le contenu et l'image
        dto.setContent(content != null ? content : "");
        dto.setImage(imageUrl != null ? imageUrl : "");
        
        // Informations temporelles
        dto.setTime(post.getCreatedAt());
        
        // Informations sur l'auteur
        User author = post.getUser();
        dto.setName(author.getDisplayName() != null ? author.getDisplayName() : author.getUsername());
        dto.setHandle("@" + author.getUsername());
        dto.setAvatar(getAvatarForUser(author));
        
        // Statistiques
        dto.setComments(post.getComments().size());
        dto.setLikes(likeService.countLikesByPost(post));
        
        // Convertir les commentaires en DTO
        List<CommentDto> commentDtos = post.getComments().stream()
            .map(comment -> {
                CommentDto commentDto = new CommentDto();
                commentDto.setId(comment.getId());
                commentDto.setContent(comment.getContent());
                commentDto.setCreatedAt(comment.getCreatedAt());
                commentDto.setUserName(comment.getUser().getDisplayName() != null ? 
                    comment.getUser().getDisplayName() : comment.getUser().getUsername());
                commentDto.setUserAvatar(getAvatarForUser(comment.getUser()));
                commentDto.setLikes(likeService.countLikesByComment(comment));
                return commentDto;
            })
            .collect(Collectors.toList());
        
        dto.setCommentList(commentDtos);
        
        return dto;
    }

    private String cleanPostContent(String content, String imageUrl) {
        if (content == null) {
            return "";
        }
        
        // Nettoyer le contenu des URLs d'images
        String cleanedContent = content.replaceAll("(?:/images/[^\\s]+\\.(jpg|jpeg|png|gif|bmp))", "").trim();
        
        // Si le contenu est vide après nettoyage, retourner une chaîne vide
        return cleanedContent.isEmpty() ? "" : cleanedContent;
    }

    // Dummy method pour l'avatar — adapte si tu as une vraie logique
    private String getAvatarForUser(User user) {
        if (user.getProfileImageUrl() != null && !user.getProfileImageUrl().isEmpty()) {
            return user.getProfileImageUrl();
        } else {
            // Par défaut, initiales
            return user.getDisplayName() != null && !user.getDisplayName().isEmpty()
                    ? user.getDisplayName().substring(0, 1).toUpperCase()
                    : "U";
        }
    }

    public List<Post> findAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional
    public void deletePost(Post post) {
        // First, remove the post from all hashtags
        for (Hashtag hashtag : new HashSet<>(post.getHashtags())) {
            hashtag.removePost(post);
        }
        
        // Clear all collections
        post.getHashtags().clear();
        post.getLikes().clear();
        post.getComments().clear();
        
        // Save to update all relationships
        post = postRepository.save(post);
        
        // Delete the post
        postRepository.delete(post);
    }

    public Post updatePost(Post post) {
        // Clean content here as well
        post.setContent(cleanPostContent(post.getContent(), post.getImageUrl()));
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
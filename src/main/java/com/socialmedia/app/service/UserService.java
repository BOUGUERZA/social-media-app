package com.socialmedia.app.service;

import com.socialmedia.app.model.User;
import com.socialmedia.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User followUser(User currentUser, User userToFollow) {
        if (!currentUser.getFollowing().contains(userToFollow)) {
            currentUser.getFollowing().add(userToFollow);
            userToFollow.getFollowers().add(currentUser);
            userRepository.save(userToFollow);
            return userRepository.save(currentUser);
        }
        return currentUser;
    }

    public User unfollowUser(User currentUser, User userToUnfollow) {
        if (currentUser.getFollowing().contains(userToUnfollow)) {
            currentUser.getFollowing().remove(userToUnfollow);
            userToUnfollow.getFollowers().remove(currentUser);
            userRepository.save(userToUnfollow);
            return userRepository.save(currentUser);
        }
        return currentUser;
    }

    public User updateProfile(User user) {
        return userRepository.save(user);
    }
    
    public User blockUser(User currentUser, User userToBlock) {
        if (!currentUser.getBlockedUsers().contains(userToBlock)) {
            // Add to blocked users
            currentUser.getBlockedUsers().add(userToBlock);
            
            // Remove from following if they were following
            if (currentUser.getFollowing().contains(userToBlock)) {
                unfollowUser(currentUser, userToBlock);
            }
            
            // Remove from followers if they were followers
            if (userToBlock.getFollowing().contains(currentUser)) {
                unfollowUser(userToBlock, currentUser);
            }
            
            return userRepository.save(currentUser);
        }
        return currentUser;
    }
    
    public User unblockUser(User currentUser, User userToUnblock) {
        if (currentUser.getBlockedUsers().contains(userToUnblock)) {
            currentUser.getBlockedUsers().remove(userToUnblock);
            return userRepository.save(currentUser);
        }
        return currentUser;
    }
    
    public List<User> getBlockedUsers(User user) {
        return user.getBlockedUsers();
    }
    
    public boolean isUserBlocked(User user, User potentiallyBlockedUser) {
        return user.getBlockedUsers().contains(potentiallyBlockedUser);
    }
    
    public List<User> searchUsersByUsernameOrDisplayName(String searchTerm) {
        return userRepository.findByUsernameContainingOrDisplayNameContaining(searchTerm, searchTerm);
    }
    
    public List<User> findUserSuggestions(User currentUser) {
        if (currentUser == null) {
            return userRepository.findAll().subList(0, Math.min(5, userRepository.findAll().size()));
        }
        
        // Récupérer tous les utilisateurs sauf l'utilisateur courant et ceux qu'il suit déjà
        List<User> allUsers = userRepository.findAll();
        List<User> suggestions = new ArrayList<>();
        
        for (User user : allUsers) {
            if (!user.getId().equals(currentUser.getId()) && 
                !currentUser.getFollowing().contains(user) &&
                !currentUser.getBlockedUsers().contains(user)) {
                suggestions.add(user);
            }
        }
        
        // Limiter à 5 suggestions
        return suggestions.subList(0, Math.min(5, suggestions.size()));
    }
}

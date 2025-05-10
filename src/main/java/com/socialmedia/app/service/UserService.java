package com.socialmedia.app.service;

import com.socialmedia.app.model.User;
import com.socialmedia.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}

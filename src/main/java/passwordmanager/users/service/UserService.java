package passwordmanager.users.service;

import org.springframework.stereotype.Service;
import passwordmanager.users.entity.User;
import passwordmanager.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create a new user
    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        user.setCreatedAt(LocalDateTime.now());
        user.setEnabled(true);
        return userRepository.save(user);
    }

    // Fetch all users (excluding soft-deleted ones)
    public List<User> getAllUsers() {
        return userRepository.findAll().stream()
                .filter(user -> user.getDeletedAt() == null)
                .toList();
    }

    // Fetch all users, including deleted ones
    public List<User> getAllUsersIncludingDeleted() {
        return userRepository.findAll();
    }

    // Fetch a single user by email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Update user by email
    public User updateUser(String email, User updatedUser) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setRole(updatedUser.getRole());
        user.setEnabled(updatedUser.getEnabled());
        user.setUpdatedAt(LocalDateTime.now());
        user.setUpdatedBy(updatedUser.getUpdatedBy());


        return userRepository.save(user);
    }

    // Soft delete user by email
    public void softDeleteUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setDeletedAt(LocalDateTime.now());
        user.setEnabled(false);
        userRepository.save(user);
    }
}
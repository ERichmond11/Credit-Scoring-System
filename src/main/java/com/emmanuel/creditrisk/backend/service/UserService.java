package com.emmanuel.creditrisk.backend.service;

import com.emmanuel.creditrisk.backend.entity.User;
import com.emmanuel.creditrisk.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String name, String email, String rawPassword) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }

        String hashed = passwordEncoder.encode(rawPassword);
        User user = new User(name, email, hashed);
        return userRepository.save(user);
    }

    public Optional<User> authenticate(String email, String rawPassword) {
        return userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(rawPassword, user.getPasswordHash()));
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
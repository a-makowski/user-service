package com.makowski.user_service.service;

import com.makowski.user_service.entity.User;
import com.makowski.user_service.exceptions.InvalidRequestException;
import com.makowski.user_service.exceptions.NoSuchUserException;
import com.makowski.user_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public User createUser(User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) throw new InvalidRequestException(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) return user.get();
        else throw new NoSuchUserException();
    }
}
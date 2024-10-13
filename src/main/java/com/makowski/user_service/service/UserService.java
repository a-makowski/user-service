package com.makowski.user_service.service;

import com.makowski.user_service.entity.User;
import com.makowski.user_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User createUser(User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) throw new RuntimeException();
                //change exception!!!!
        // password encode
        return userRepository.save(user);
    }



}

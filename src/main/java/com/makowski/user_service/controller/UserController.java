package com.makowski.user_service.controller;

import com.makowski.user_service.entity.User;
import com.makowski.user_service.service.TokenService;
import com.makowski.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/validate-token")
    public ResponseEntity<String> validateTokenGetUsername(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tokenService.validateTokenGetUsername(token));
    }

}
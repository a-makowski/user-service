package com.makowski.user_service.controller;

import com.makowski.user_service.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/validate-token")
public class TokenController {

    private TokenService tokenService;
    @PostMapping
    public ResponseEntity<String> validateTokenGetUsername(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tokenService.validateTokenGetUsername(token));
    }
}
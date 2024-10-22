package com.makowski.user_service.dto;

import lombok.Data;

@Data
public class TokenRequest {
    private String token;
    private String username;
}

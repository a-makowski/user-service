package com.makowski.user_service.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.makowski.user_service.dto.TokenRequest;
import com.makowski.user_service.exceptions.WrongUserException;
import com.makowski.user_service.security.SecurityConstants;

import org.springframework.stereotype.Service;


import java.util.Date;


@Service
public class TokenService {

    private final Algorithm algorithm = Algorithm.HMAC512(SecurityConstants.SECRET_KEY);

    public boolean validateToken(TokenRequest tokenRequest) {
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT decodedJWT = verifier.verify(tokenRequest.getToken());
            if (decodedJWT.getExpiresAt().before(new Date())) throw new JWTVerificationException("JWT has expired");
            if (!decodedJWT.getSubject().equals(tokenRequest.getUsername())) throw new WrongUserException();
            return true;
    }
}

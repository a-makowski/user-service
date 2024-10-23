package com.makowski.user_service.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.makowski.user_service.security.SecurityConstants;

import org.springframework.stereotype.Service;


@Service
public class TokenService {

    private final Algorithm algorithm = Algorithm.HMAC512(SecurityConstants.SECRET_KEY);

    public String validateTokenGetUsername(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
       // if (decodedJWT.getExpiresAt().before(new Date())) throw new JWTVerificationException("JWT has expired");
            //TODO zmienic blad i obsluzyc oba
        return decodedJWT.getSubject();
    }
}
package com.makowski.user_service.controller;

import com.makowski.user_service.exceptions.ErrorResponse;
import com.makowski.user_service.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/validate-token")
@Tag(name = "Token Management", description = "Handles token validation requests from other microservices.")
public class TokenController {

    private TokenService tokenService;

    @Operation(summary = "Validate token and return username", description = "Validates token and, if successful, returns owner's username.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful validation and return of token owner's username", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Incorrect token", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "JWT Token not valid", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<String> validateTokenGetUsername(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(tokenService.validateTokenGetUsername(token), HttpStatus.OK);
    }
}
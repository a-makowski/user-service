package com.makowski.user_service.exceptions;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException() {
        super("Invalid request");
    }

    public InvalidRequestException(String username) {
        super("username " + username + " is already taken");
    }
}

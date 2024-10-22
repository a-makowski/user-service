package com.makowski.user_service.exceptions;

public class WrongUserException extends RuntimeException {
    public WrongUserException() {
        super("Token doesn't match username");
    }
}
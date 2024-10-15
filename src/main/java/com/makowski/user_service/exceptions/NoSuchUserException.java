package com.makowski.user_service.exceptions;

public class NoSuchUserException extends RuntimeException{
    public NoSuchUserException() {
        super("There's no such user.");
    }
}
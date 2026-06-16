package com.enalab.board.auth.infrastructure.exception;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("User not found. Name: " + username);
    }

    public UserNotFoundException(UUID userId){
        super("User not found. ID: " + userId);
    }
}

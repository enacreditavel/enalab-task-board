package com.enalab.board.auth.infrastructure.exception;

import java.util.UUID;

public class ProfileNotFoundException extends RuntimeException {
    public ProfileNotFoundException(String profileName) {
        super("Profile not found. Name: " + profileName);
    }

    public ProfileNotFoundException(UUID profileId){
        super("Profile not found. ID: " + profileId);
    }
}

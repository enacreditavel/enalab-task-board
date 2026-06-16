package com.enalab.board.auth.infrastructure.api.dto;

import com.enalab.board.auth.application.user.output.UserOutput;

import java.util.UUID;

public record UserResponse(UUID id, String username) {
    public static UserResponse from(UserOutput output){
        return new UserResponse(output.id(), output.username());
    }
}

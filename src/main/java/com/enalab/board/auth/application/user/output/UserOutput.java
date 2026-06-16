package com.enalab.board.auth.application.user.output;

import com.enalab.board.auth.domain.User;

import java.time.Instant;
import java.util.UUID;

public record UserOutput(UUID id, String username, String email, Instant createdAt) {
    public static UserOutput from(User user) {
        return new UserOutput(user.getUserId().id(), user.getUsername(), user.getEmail(), user.getCreatedAt());

    }
}

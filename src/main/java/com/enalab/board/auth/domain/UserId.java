package com.enalab.board.auth.domain;

import java.util.UUID;

public record UserId(UUID id) {
    public static UserId generate(UUID id) {
        return new UserId(id);
    }
    public static UserId generate() {
        return new UserId(UUID.randomUUID());
    }
}

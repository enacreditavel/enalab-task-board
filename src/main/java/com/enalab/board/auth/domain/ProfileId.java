package com.enalab.board.auth.domain;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public record ProfileId(UUID id) {
    public static ProfileId generate(UUID id) {
        return new ProfileId(id);
    }
    public static ProfileId generate(String id) {
        log.info("Generating ProfileId from String: {}", id);
        return new ProfileId(UUID.fromString(id));
    }

    public static ProfileId generate() {
        return new ProfileId(UUID.randomUUID());
    }
}

package com.enalab.board.auth.domain;

import java.util.UUID;

public record PermissionId(UUID id) {
    public static PermissionId generate(UUID id) {
        return new PermissionId(id);
    }

    public static PermissionId generate(String id) {
        return new PermissionId(UUID.fromString(id));
    }

    public static PermissionId generate() {
        return new PermissionId(UUID.randomUUID());
    }
}

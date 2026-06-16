package com.enalab.board.auth.domain;

import java.util.UUID;

public record Permission(PermissionId permissionId, String name) {
    public static Permission create(UUID id, String name) {
        return new Permission(PermissionId.generate(id),name.toUpperCase().trim());
    }
    public static Permission create(String name) {
        return new Permission(PermissionId.generate(),name.toUpperCase().trim());
    }
}

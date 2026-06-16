package com.enalab.board.auth.application.permission.output;

import com.enalab.board.auth.domain.Permission;

import java.util.UUID;

public record PermissionOutput(UUID id, String name) {
    public static PermissionOutput from(Permission permission) {
        return new PermissionOutput(permission.permissionId().id(), permission.name());
    }
}

package com.enalab.board.auth.infrastructure.api.dto;

import com.enalab.board.auth.application.permission.output.PermissionOutput;

import java.util.UUID;

public record PermissionResponse(UUID id, String name) {
    public static PermissionResponse from(PermissionOutput output) {
        return new PermissionResponse(output.id(), output.name());
    }
}

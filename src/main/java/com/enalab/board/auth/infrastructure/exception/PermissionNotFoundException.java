package com.enalab.board.auth.infrastructure.exception;

import java.util.UUID;

public class PermissionNotFoundException extends RuntimeException {
    public PermissionNotFoundException(String permissionName) {
        super("Permission not found. Name: " + permissionName);
    }

    public PermissionNotFoundException(UUID permissionId){
        super("Permission not found. ID: " + permissionId);
    }
}

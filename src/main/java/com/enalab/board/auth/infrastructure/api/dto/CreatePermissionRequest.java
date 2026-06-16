package com.enalab.board.auth.infrastructure.api.dto;

import com.enalab.board.auth.application.permission.input.CreatePermissionInput;

public record CreatePermissionRequest(String name) {
    public CreatePermissionInput toInput(){
        return new CreatePermissionInput(name);
    }
}

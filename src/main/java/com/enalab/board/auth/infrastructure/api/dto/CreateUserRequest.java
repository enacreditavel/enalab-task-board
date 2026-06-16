package com.enalab.board.auth.infrastructure.api.dto;

import com.enalab.board.auth.application.user.input.CreateUserInput;

public record CreateUserRequest(String username, String name, String email, String password) {
    public CreateUserInput toInput() {
        return new CreateUserInput(username, name, email, password);
    }
}

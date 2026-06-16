package com.enalab.board.auth.application.user.input;

public record CreateUserInput(String username, String name, String email, String password) {
}

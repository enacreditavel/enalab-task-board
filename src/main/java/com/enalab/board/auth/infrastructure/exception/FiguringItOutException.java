package com.enalab.board.auth.infrastructure.exception;

public class FiguringItOutException extends RuntimeException {
    public FiguringItOutException(String message) {
        super("That's the problem: " + message);
    }
}

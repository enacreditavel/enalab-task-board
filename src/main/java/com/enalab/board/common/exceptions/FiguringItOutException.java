package com.enalab.board.common.exceptions;

public class FiguringItOutException extends RuntimeException {
    public FiguringItOutException(String message) {
        super("That's the problem: " + message);
    }
}

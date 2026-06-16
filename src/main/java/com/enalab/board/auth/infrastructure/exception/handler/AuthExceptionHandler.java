package com.enalab.board.auth.infrastructure.exception.handler;

import com.enalab.board.auth.infrastructure.exception.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserByIdNotFoundException(UserNotFoundException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(PermissionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlePermissionByIdNotFoundException(PermissionNotFoundException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(ProfileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleProfileByIdNotFoundException(ProfileNotFoundException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleConflict(DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: " + e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public String figuringItOut(AuthenticationException ex) {
        return ex.getMessage();
    }
}

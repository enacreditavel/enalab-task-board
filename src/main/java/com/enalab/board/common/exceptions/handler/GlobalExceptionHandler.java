package com.enalab.board.common.exceptions.handler;

import com.enalab.board.common.exceptions.AlreadyExistsException;
import com.enalab.board.common.exceptions.FiguringItOutException;
import com.enalab.board.common.exceptions.InvalidFormatException;
import com.enalab.board.common.exceptions.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Hidden
    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidFormatException(InvalidFormatException ex){
        return ex.getMessage();
    }

    @Hidden
    @ExceptionHandler(FiguringItOutException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleFiguringItOutException(FiguringItOutException ex){
        return ex.getMessage();
    }

    @Hidden
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException(ResourceNotFoundException ex){
        return ex.getMessage();
    }

    @Hidden
    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleAlreadyExistsException(AlreadyExistsException ex) {
        return ex.getMessage();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "Access denied for lack of privileges",
                    content = @Content(schema = @Schema(example = "Unauthorized: Login is required to access this feature.")))
    })
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleAuthenticationException() {
        return "Unauthorized: Login is required to access this feature.";
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "Access denied for lack of privileges",
                    content = @Content(schema = @Schema(example = "Access denied: User does not have privileges to perform this action.")))
    })
    @ExceptionHandler(AuthorizationDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleAccessDeniedException() {
        return "Access denied: User does not have privileges to perform this action.";
    }

}

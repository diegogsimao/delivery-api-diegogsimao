package com.deliverytech.delivery.exceptions;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.Map;

import javax.security.sasl.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SecurityExceptionHandler {
    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> handleAuthenticationCredentialsNotFound(AuthenticationCredentialsNotFoundException ex) {
        return buildResponse("Credenciais inválidas ou ausente", HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> handleAuthentication(AuthenticationException ex) {
        return buildResponse("Acesso Negado", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, Object> handleAccessDenied(AccessDeniedException ex) {
        return buildResponse("Acesso Negado", HttpStatus.FORBIDDEN);
    }

    private Map<String, Object> buildResponse(String message, HttpStatus status) {
        return Map.of(
                "timeStamp", LocalDateTime.now().toString(),
                "status", status.value(),
                "error", status.getReasonPhrase(),
                "message", message);
    }
}

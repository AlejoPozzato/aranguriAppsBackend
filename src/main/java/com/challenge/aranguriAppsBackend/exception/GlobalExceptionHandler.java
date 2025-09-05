package com.challenge.aranguriAppsBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handleUsuarioNoEncontrado(UsuarioNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(UsuarioYaExistenteException.class)
    public ResponseEntity<Map<String, String>> handleEmailYaRegistrado(UsuarioYaExistenteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error",ex.getMessage()));
    }

    @ExceptionHandler(CredencialesIncorrectasException.class)
    public ResponseEntity<Map<String, String>> handleCredencialesIncorrectas(CredencialesIncorrectasException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class) // cualquier excepción no controlada
    public ResponseEntity<Map<String, String>> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error","Error inesperado: " + ex.getMessage()));
    }

}

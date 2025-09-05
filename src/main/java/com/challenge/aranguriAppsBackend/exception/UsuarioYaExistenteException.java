package com.challenge.aranguriAppsBackend.exception;

public class UsuarioYaExistenteException extends RuntimeException {
    public UsuarioYaExistenteException(String message) {
        super(message);
    }
}

package com.challenge.aranguriAppsBackend.dto;

public record LoginResponse(String token, int id, String nombre, String email) {
}

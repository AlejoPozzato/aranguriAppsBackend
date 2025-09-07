package com.challenge.aranguriAppsBackend.controller;

import com.challenge.aranguriAppsBackend.dto.ArchivoRequest;
import com.challenge.aranguriAppsBackend.dto.ArchivoResponse;
import com.challenge.aranguriAppsBackend.model.Archivo;
import com.challenge.aranguriAppsBackend.service.ArchivoService;
import com.challenge.aranguriAppsBackend.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materias/{materiaId}/archivos")
public class ArchivoController {

    private final ArchivoService archivoService;
    private final JwtService jwtService;

    public ArchivoController(JwtService jwtService, ArchivoService archivoService) {
        this.jwtService = jwtService;
        this.archivoService = archivoService;
    }

    //Creo archivo
    @PostMapping
    public ResponseEntity<ArchivoResponse> crearArchivo(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable int materiaId,
            @RequestBody ArchivoRequest request
    ) {
        String token = authHeader.replace("Bearer ", "");
        int usuarioId = jwtService.extraerUserId(token);

        Archivo archivo = archivoService.crearArchivo(usuarioId, materiaId, request);
        ArchivoResponse response = new ArchivoResponse(
                archivo.getId(),
                archivo.getNombre(),
                archivo.getTipo(),
                archivo.getUrl()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Listar archivos
    @GetMapping
    public ResponseEntity<List<ArchivoResponse>> listarArchivos(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable int materiaId
    ) {
        String token = authHeader.replace("Bearer ", "");
        int usuarioId = jwtService.extraerUserId(token);

        List<ArchivoResponse> archivos = archivoService.listarArchivos(usuarioId, materiaId)
                .stream()
                .map(a -> new ArchivoResponse(a.getId(), a.getNombre(), a.getTipo(), a.getUrl()))
                .toList();

        return ResponseEntity.ok(archivos);
    }

    //Eliminar archivos
    @DeleteMapping("/{archivoId}")
    public ResponseEntity<Void> eliminarArchivo(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable int materiaId,
            @PathVariable int archivoId
    ) {
        String token = authHeader.replace("Bearer ", "");
        int usuarioId = jwtService.extraerUserId(token);

        archivoService.eliminarArchivo(usuarioId, materiaId, archivoId);
        return ResponseEntity.noContent().build(); //204
    }
}

package com.challenge.aranguriAppsBackend.service;

import com.challenge.aranguriAppsBackend.dto.ArchivoRequest;
import com.challenge.aranguriAppsBackend.exception.ArchivoNoPerteneceAMateriaException;
import com.challenge.aranguriAppsBackend.exception.ArchivoNotFoundException;
import com.challenge.aranguriAppsBackend.exception.NoPermissionException;
import com.challenge.aranguriAppsBackend.model.Archivo;
import com.challenge.aranguriAppsBackend.model.Materia;
import com.challenge.aranguriAppsBackend.repository.ArchivoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ArchivoService {

    private final ArchivoRepository archivoRepository;
    private final MateriaService materiaService;

    public ArchivoService(ArchivoRepository archivoRepository, MateriaService materiaService) {
        this.archivoRepository = archivoRepository;
        this.materiaService = materiaService;
    }

    @Transactional
    public Archivo crearArchivo(int usuarioId, int materiaId, ArchivoRequest request) {
        Materia materia = materiaService.buscarPorId(materiaId);

        if (materia.getUsuario().getId() != usuarioId) {
            throw new NoPermissionException("No tiene permiso para agregar archivos en esta materia");
        }

        Archivo archivo = new Archivo();
        archivo.setNombre(request.nombre());
        archivo.setTipo(request.tipo());

        //Genero una URL simulada para el mock del archivo
        String url = StringUtils.hasText(request.url())
                ? request.url()
                : "/api/materias/" + materiaId + "/archivos/" + archivo.getId();
        archivo.setUrl(url);

        archivo.setMateria(materia);

        return archivoRepository.save(archivo);
    }

    public List<Archivo> listarArchivos(int usuarioId, int materiaId) {
        Materia materia = materiaService.buscarPorId(materiaId);

        if (materia.getUsuario().getId() != usuarioId) {
            throw new NoPermissionException("No tiene permiso para ver estos archivos");
        }

        return archivoRepository.findByMateriaId(materiaId);
    }

    @Transactional
    public void eliminarArchivo(int usuarioId, int materiaId, int archivoId) {
        Materia materia = materiaService.buscarPorId(materiaId);

        if (materia.getUsuario().getId() != usuarioId) {
            throw new NoPermissionException("No tiene permiso para eliminar archivos en esta materia");
        }

        Archivo archivo = archivoRepository.findById(archivoId)
                .orElseThrow(() -> new ArchivoNotFoundException("Archivo no encontrado"));

        if (archivo.getMateria().getId() != materiaId) {
            throw new ArchivoNoPerteneceAMateriaException("El archivo no pertenece a esta materia");
        }

        archivoRepository.delete(archivo);
    }
}

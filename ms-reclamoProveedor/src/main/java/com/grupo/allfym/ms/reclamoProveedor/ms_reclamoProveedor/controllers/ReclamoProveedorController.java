package com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.controllers;

import com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.models.dto.ReclamoProveedorRequestDTO;
import com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.models.dto.ReclamoProveedorResponseDTO;
import com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.models.entities.ReclamoProveedor;
import com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.services.ReclamoProveedorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reclamo-proveedor")
public class ReclamoProveedorController {

    private static final Logger logger = LoggerFactory.getLogger(ReclamoProveedorController.class);

    @Autowired
    private ReclamoProveedorService reclamoService;

    // === ENDPOINTS PRINCIPALES CON DTOs ===

    @PostMapping
    public ResponseEntity<ReclamoProveedorResponseDTO> crearReclamo(@RequestBody ReclamoProveedorRequestDTO reclamoRequest) {
        logger.info("Recibiendo petición para crear reclamo de proveedor ID: {}", reclamoRequest.getIdProveedor());
        try {
            ReclamoProveedorResponseDTO reclamo = reclamoService.crearReclamo(reclamoRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(reclamo);
        } catch (Exception e) {
            logger.error("Error al crear reclamo: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReclamoProveedorResponseDTO> obtenerReclamo(@PathVariable Long id) {
        Optional<ReclamoProveedorResponseDTO> reclamo = reclamoService.obtenerReclamoPorId(id);
        return reclamo.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ReclamoProveedorResponseDTO>> obtenerTodosLosReclamos() {
        List<ReclamoProveedorResponseDTO> reclamos = reclamoService.obtenerTodosLosReclamos();
        return ResponseEntity.ok(reclamos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReclamoProveedorResponseDTO> actualizarReclamo(
            @PathVariable Long id,
            @RequestBody ReclamoProveedorRequestDTO reclamoRequest) {
        try {
            ReclamoProveedorResponseDTO reclamo = reclamoService.actualizarReclamo(id, reclamoRequest);
            return ResponseEntity.ok(reclamo);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReclamo(@PathVariable Long id) {
        try {
            reclamoService.eliminarReclamo(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // === ENDPOINTS DE MÉTODOS DE DOMINIO ===

    /**
     * reclamo() - Crear reclamo básico
     */
    @PostMapping("/crear-basico")
    public ResponseEntity<ReclamoProveedorResponseDTO> crearReclamoBasico(
            @RequestParam String descripcion,
            @RequestParam Long idProveedor,
            @RequestParam(required = false) String nroOrden) {
        try {
            ReclamoProveedorResponseDTO reclamo = reclamoService.reclamo(descripcion, idProveedor, nroOrden);
            return ResponseEntity.status(HttpStatus.CREATED).body(reclamo);
        } catch (Exception e) {
            logger.error("Error al crear reclamo básico: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * agregarReclamo() - Agregar información adicional
     */
    @PutMapping("/{id}/agregar-informacion")
    public ResponseEntity<ReclamoProveedorResponseDTO> agregarInformacionReclamo(
            @PathVariable Long id,
            @RequestParam String descripcionAdicional) {
        try {
            ReclamoProveedorResponseDTO reclamo = reclamoService.agregarReclamo(id, descripcionAdicional);
            return ResponseEntity.ok(reclamo);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * FiltrarPorEstado() - Filtrar por estado
     */
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ReclamoProveedorResponseDTO>> filtrarPorEstado(@PathVariable String estado) {
        try {
            List<ReclamoProveedorResponseDTO> reclamos = reclamoService.filtrarPorEstado(estado);
            return ResponseEntity.ok(reclamos);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * FiltrarPorIdproveedor() - Filtrar por proveedor
     */
    @GetMapping("/proveedor/{idProveedor}")
    public ResponseEntity<List<ReclamoProveedorResponseDTO>> filtrarPorProveedor(@PathVariable Long idProveedor) {
        try {
            List<ReclamoProveedorResponseDTO> reclamos = reclamoService.filtrarPorIdProveedor(idProveedor);
            return ResponseEntity.ok(reclamos);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // === ENDPOINTS ADICIONALES ===

    @PutMapping("/{id}/cambiar-estado")
    public ResponseEntity<ReclamoProveedorResponseDTO> cambiarEstado(
            @PathVariable Long id,
            @RequestParam String nuevoEstado) {
        try {
            ReclamoProveedorResponseDTO reclamo = reclamoService.cambiarEstadoReclamo(id, nuevoEstado);
            return ResponseEntity.ok(reclamo);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/proveedor/{idProveedor}/estado/{estado}")
    public ResponseEntity<List<ReclamoProveedorResponseDTO>> obtenerReclamosPorProveedorYEstado(
            @PathVariable Long idProveedor,
            @PathVariable String estado) {
        try {
            List<ReclamoProveedorResponseDTO> reclamos = reclamoService.obtenerReclamosPorProveedorYEstado(idProveedor, estado);
            return ResponseEntity.ok(reclamos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/proveedor/{idProveedor}/pendientes")
    public ResponseEntity<List<ReclamoProveedorResponseDTO>> obtenerReclamosPendientes(@PathVariable Long idProveedor) {
        List<ReclamoProveedorResponseDTO> reclamos = reclamoService.obtenerReclamosPendientesPorProveedor(idProveedor);
        return ResponseEntity.ok(reclamos);
    }

    @GetMapping("/buscar/nro-orden/{nroOrden}")
    public ResponseEntity<ReclamoProveedorResponseDTO> buscarPorNroOrden(@PathVariable String nroOrden) {
        Optional<ReclamoProveedorResponseDTO> reclamo = reclamoService.buscarPorNroOrden(nroOrden);
        return reclamo.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // === ENDPOINTS LEGACY (Para compatibilidad) ===

    @GetMapping("/legacy")
    public ResponseEntity<List<ReclamoProveedor>> listarLegacy() {
        return ResponseEntity.ok(reclamoService.listar());
    }

    @PostMapping("/legacy")
    public ResponseEntity<ReclamoProveedor> guardarLegacy(@RequestBody ReclamoProveedor reclamo) {
        try {
            ReclamoProveedor reclamoGuardado = reclamoService.guardar(reclamo);
            return ResponseEntity.status(HttpStatus.CREATED).body(reclamoGuardado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // === ENDPOINT DE INFORMACIÓN ===
    @GetMapping("/info/estados")
    public ResponseEntity<String[]> obtenerEstadosDisponibles() {
        String[] estados = {"PENDIENTE", "EN_INVESTIGACION", "ACEPTADO", "RECHAZADO", "CERRADO"};
        return ResponseEntity.ok(estados);
    }
}

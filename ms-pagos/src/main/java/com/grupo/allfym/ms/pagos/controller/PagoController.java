package com.grupo.allfym.ms.pagos.controller;

import com.grupo.allfym.ms.pagos.models.PagoRequestDTO;
import com.grupo.allfym.ms.pagos.models.PagoResponseDTO;
import com.grupo.allfym.ms.pagos.services.PagoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "*")
public class PagoController {

    private static final Logger logger = LoggerFactory.getLogger(PagoController.class);

    private final PagoService pagoService;

    @Autowired
    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    // === ENDPOINTS ESENCIALES ===

    @PostMapping
    public ResponseEntity<PagoResponseDTO> crearPago(@Valid @RequestBody PagoRequestDTO pagoRequest) {
        try {
            logger.info("Creando pago para venta ID: {}", pagoRequest.getVentaId());
            PagoResponseDTO pagoCreado = pagoService.crearPago(pagoRequest);
            logger.info("Pago creado exitosamente con ID: {}", pagoCreado.getIdPago());
            return ResponseEntity.status(HttpStatus.CREATED).body(pagoCreado);
        } catch (RuntimeException e) {
            logger.error("Error al crear pago: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            logger.error("Error inesperado al crear pago", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoResponseDTO> obtenerPago(@PathVariable Long id) {
        try {
            logger.debug("Obteniendo pago con ID: {}", id);
            return pagoService.obtenerPagoPorId(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            logger.error("Error al obtener pago con ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PagoResponseDTO>> listarPagos(
            @RequestParam(required = false) Long ventaId,
            @RequestParam(required = false) String estado) {
        try {
            List<PagoResponseDTO> pagos;

            if (ventaId != null) {
                logger.debug("Obteniendo pagos para venta ID: {}", ventaId);
                pagos = pagoService.obtenerPagosPorVenta(ventaId);
            } else if (estado != null) {
                logger.debug("Obteniendo pagos con estado: {}", estado);
                pagos = pagoService.obtenerTodosLosPagos().stream()
                    .filter(pago -> estado.equalsIgnoreCase(pago.getEstadoPago()))
                    .toList();
            } else {
                logger.debug("Obteniendo todos los pagos");
                pagos = pagoService.obtenerTodosLosPagos();
            }

            return ResponseEntity.ok(pagos);
        } catch (Exception e) {
            logger.error("Error al obtener pagos: {}", e.getMessage());
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoResponseDTO> actualizarEstadoPago(
            @PathVariable Long id,
            @RequestParam String estado) {
        try {
            logger.info("Actualizando estado del pago {} a: {}", id, estado);
            PagoResponseDTO pagoActualizado = pagoService.actualizarEstadoPago(id, estado);
            logger.info("Estado del pago {} actualizado exitosamente", id);
            return ResponseEntity.ok(pagoActualizado);
        } catch (RuntimeException e) {
            logger.error("Error al actualizar estado del pago {}: {}", id, e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            logger.error("Error inesperado al actualizar estado del pago {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long id) {
        try {
            logger.info("Eliminando pago con ID: {}", id);
            if (pagoService.porId(id).isPresent()) {
                pagoService.eliminar(id);
                logger.info("Pago {} eliminado exitosamente", id);
                return ResponseEntity.noContent().build();
            } else {
                logger.warn("Intento de eliminar pago inexistente con ID: {}", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error al eliminar pago {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Microservicio de Pagos funcionando correctamente");
    }
}

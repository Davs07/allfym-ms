package com.grupo.allfym.ms_compra.controllers;

import com.grupo.allfym.ms_compra.models.CambiarEstadoNuevo;
import com.grupo.allfym.ms_compra.models.dto.CompraRequestDTO;
import com.grupo.allfym.ms_compra.models.dto.CompraResponseDTO;
import com.grupo.allfym.ms_compra.models.entities.Compra;
import com.grupo.allfym.ms_compra.models.enums.Estado;
import com.grupo.allfym.ms_compra.services.CompraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/compra")
public class CompraController {

    private static final Logger logger = LoggerFactory.getLogger(CompraController.class);

    @Autowired
    private CompraService compraService;

    @GetMapping
    public ResponseEntity<List<Compra>> listar() {
        return ResponseEntity.ok(compraService.listar());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<Compra> op = compraService.buscarPorId(id);

        if(op.isPresent()){
            Compra compra = op.get();
            return ResponseEntity.status(HttpStatus.OK).body(compra);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Compra>> buscarPorEstado(@PathVariable String estado) {
        Estado estadoEnum = Estado.valueOf(estado.toUpperCase());
        return ResponseEntity.ok(compraService.buscarPorEstado(estadoEnum));
    }

    @GetMapping("/proveedor/{idProveedor}")
    public ResponseEntity<List<Compra>> buscarPorIdProveedor(@PathVariable Long idProveedor) {
        return ResponseEntity.ok(compraService.buscarPorIdProveedor(idProveedor));
    }

    @GetMapping("/proveedor/nombre/{nombre}")
    public ResponseEntity<List<Compra>> buscarPorNombreProveedor(@PathVariable String nombre) {
        return ResponseEntity.ok(compraService.buscarPorNombreProveedor(nombre));
    }

    // === NUEVO ENDPOINT CON DTOs (RECOMENDADO) ===
    @PostMapping("/nueva")
    public ResponseEntity<CompraResponseDTO> crearCompra(@RequestBody CompraRequestDTO compraRequest) {
        logger.info("Recibiendo petición para crear nueva compra con DTOs");
        logger.info("Proveedor ID: {}", compraRequest.getProveedorId());
        logger.info("Monto total: {}", compraRequest.getMontoTotal());
        logger.info("Observaciones: {}", compraRequest.getObservaciones());

        try {
            CompraResponseDTO compraCreada = compraService.crearCompra(compraRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(compraCreada);
        } catch (Exception e) {
            logger.error("Error al crear compra: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // === ENDPOINT LEGACY (MANTENIDO PARA COMPATIBILIDAD) ===
    @PostMapping
    public ResponseEntity<Compra> guardar(@RequestBody Compra compra) {
        logger.info("Recibiendo petición para guardar compra");
        logger.info("ID Proveedor recibido: {}", compra.getIdProveedor());
        logger.info("Monto total: {}", compra.getMontoTotal());
        logger.info("Observaciones: {}", compra.getObservaciones());
        logger.info("Estado: {}", compra.getEstado());
        logger.info("Detalles: {}", compra.getDetalles() != null ? compra.getDetalles().size() : 0);

        if (compra.getIdProveedor() == null) {
            logger.error("ERROR: El ID del proveedor es null en el objeto recibido");
            logger.error("SUGERENCIA: Use el endpoint /api/compra/nueva con CompraRequestDTO");
            return ResponseEntity.badRequest().build();
        }

        try {
            Compra guardada = compraService.guardar(compra);
            return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
        } catch (Exception e) {
            logger.error("Error al guardar compra: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/actualizarEstado/{id}")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long id, @RequestBody CambiarEstadoNuevo nuevoEstado) {
        try {
            compraService.cambiarEstado(id, nuevoEstado.getEstado().toString());
            return ResponseEntity.ok("Estado actualizado");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminarCompra (@PathVariable Long id){
        Optional<Compra> op = compraService.buscarPorId(id);
        if(op.isPresent()){
            compraService.eliminar(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}

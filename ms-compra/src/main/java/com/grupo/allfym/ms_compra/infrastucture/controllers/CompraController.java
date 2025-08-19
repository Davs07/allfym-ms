package com.grupo.allfym.ms_compra.infrastucture.controllers;

import com.grupo.allfym.ms_compra.application.services.CompraService;
import com.grupo.allfym.ms_compra.domain.models.entities.Compra;
import com.grupo.allfym.ms_compra.domain.models.entities.DetalleCompra;
import com.grupo.allfym.ms_compra.domain.models.enums.Estado;
import com.grupo.allfym.ms_compra.infrastucture.dtos.CambiarEstadoRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/compra")
public class CompraController {
    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping
    public ResponseEntity<List<Compra>> listar() {
        return ResponseEntity.ok(compraService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Compra> compra = compraService.buscarPorId(id);
        if (compra.isPresent()) {
            return ResponseEntity.ok(compra.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Compra>> buscarPorEstado(@PathVariable String estado) {
        try {
            Estado estadoEnum = Estado.valueOf(estado.toUpperCase());
            return ResponseEntity.ok(compraService.buscarPorEstado(estadoEnum));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/proveedor/{idProveedor}")
    public ResponseEntity<List<Compra>> buscarPorIdProveedor(@PathVariable Long idProveedor) {
        try {
            return ResponseEntity.ok(compraService.buscarPorIdProveedor(idProveedor));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/proveedor/nombre/{nombre}")
    public ResponseEntity<List<Compra>> buscarPorNombreProveedor(@PathVariable String nombre) {
        return ResponseEntity.ok(compraService.buscarPorNombreProveedor(nombre));
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Compra compra) {
        try {
            Compra guardada = compraService.guardar(compra);
            return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/actualizarEstado/{id}")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long id,
                                           @RequestBody CambiarEstadoRequest request) {
        try {
            compraService.cambiarEstado(id, request.getEstado().toString());
            return ResponseEntity.ok("Estado actualizado");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Optional<Compra> compra = compraService.buscarPorId(id);
        if (compra.isPresent()) {
            compraService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/detalle")
    public ResponseEntity<?> agregarDetalle(@PathVariable Long id,
                                            @RequestBody DetalleCompra detalleCompra) {
        try {
            Compra compraActualizada = compraService.agregarDetalle(id, detalleCompra);
            return ResponseEntity.ok(compraActualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

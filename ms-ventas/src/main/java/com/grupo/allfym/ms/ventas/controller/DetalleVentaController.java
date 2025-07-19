package com.grupo.allfym.ms.ventas.controller;

import com.grupo.allfym.ms.ventas.entity.DetalleVenta;
import com.grupo.allfym.ms.ventas.models.VentaRequestDTO.DetalleVentaDTO;
import com.grupo.allfym.ms.ventas.services.DetalleVentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-ventas")
@CrossOrigin(origins = "*")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @PostMapping("/venta/{ventaId}")
    public ResponseEntity<DetalleVenta> agregarDetalle(
            @PathVariable Long ventaId,
            @Valid @RequestBody DetalleVentaDTO detalleDTO) {
        try {
            DetalleVenta detalleCreado = detalleVentaService.agregarDetalle(ventaId, detalleDTO);
            return new ResponseEntity<>(detalleCreado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVenta> obtenerDetallePorId(@PathVariable Long id) {
        return detalleVentaService.buscarPorId(id)
            .map(detalle -> new ResponseEntity<>(detalle, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/venta/{ventaId}")
    public ResponseEntity<List<DetalleVenta>> obtenerDetallesPorVenta(@PathVariable Long ventaId) {
        List<DetalleVenta> detalles = detalleVentaService.buscarPorVentaId(ventaId);
        return new ResponseEntity<>(detalles, HttpStatus.OK);
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<DetalleVenta>> obtenerDetallesPorProducto(@PathVariable Long productoId) {
        List<DetalleVenta> detalles = detalleVentaService.buscarPorProducto(productoId);
        return new ResponseEntity<>(detalles, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleVenta> actualizarDetalle(
            @PathVariable Long id,
            @Valid @RequestBody DetalleVentaDTO detalleDTO) {
        try {
            DetalleVenta detalleActualizado = detalleVentaService.actualizarDetalle(id, detalleDTO);
            return new ResponseEntity<>(detalleActualizado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalle(@PathVariable Long id) {
        try {
            detalleVentaService.eliminarDetalle(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

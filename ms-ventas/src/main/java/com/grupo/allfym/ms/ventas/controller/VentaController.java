package com.grupo.allfym.ms.ventas.controller;

import com.grupo.allfym.ms.ventas.enums.EstadoVenta;
import com.grupo.allfym.ms.ventas.models.VentaRequestDTO;
import com.grupo.allfym.ms.ventas.models.VentaResponseDTO;
import com.grupo.allfym.ms.ventas.services.VentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    public ResponseEntity<VentaResponseDTO> crearVenta(@Valid @RequestBody VentaRequestDTO ventaRequest) {
        VentaResponseDTO ventaCreada = ventaService.agregarVenta(ventaRequest);
        return new ResponseEntity<>(ventaCreada, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> obtenerVentaPorId(@PathVariable Long id) {
        return ventaService.buscarPorId(id)
            .map(venta -> new ResponseEntity<>(venta, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<VentaResponseDTO>> obtenerTodasLasVentas() {
        List<VentaResponseDTO> ventas = ventaService.obtenerTodasLasVentas();
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<VentaResponseDTO>> obtenerVentasPorCliente(@PathVariable Long clienteId) {
        List<VentaResponseDTO> ventas = ventaService.buscarPorClienteId(clienteId);
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<VentaResponseDTO>> obtenerVentasPorEstado(@PathVariable EstadoVenta estado) {
        List<VentaResponseDTO> ventas = ventaService.buscarPorEstado(estado);
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<VentaResponseDTO>> obtenerVentasPorFecha(
            @RequestParam LocalDateTime fechaInicio,
            @RequestParam LocalDateTime fechaFin) {
        List<VentaResponseDTO> ventas = ventaService.buscarPorFechaRegistro(fechaInicio, fechaFin);
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }

    @PutMapping("/{id}/confirmar")
    public ResponseEntity<VentaResponseDTO> confirmarVenta(@PathVariable Long id) {
        try {
            VentaResponseDTO ventaConfirmada = ventaService.confirmarVenta(id);
            return new ResponseEntity<>(ventaConfirmada, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<VentaResponseDTO> cancelarVenta(@PathVariable Long id) {
        try {
            VentaResponseDTO ventaCancelada = ventaService.cancelarVenta(id);
            return new ResponseEntity<>(ventaCancelada, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
        try {
            ventaService.eliminarVenta(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

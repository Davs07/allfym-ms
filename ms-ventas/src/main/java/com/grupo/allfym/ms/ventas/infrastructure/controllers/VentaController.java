package com.grupo.allfym.ms.ventas.infrastructure.controllers;

import com.grupo.allfym.ms.ventas.application.services.VentaApplicationService;
import com.grupo.allfym.ms.ventas.domain.models.entities.Venta;
import com.grupo.allfym.ms.ventas.domain.models.entities.DetalleVenta;
import com.grupo.allfym.ms.ventas.domain.models.enums.EstadoVenta;
// Eliminado: ya no usamos Commands, convertimos directo a dominio
import com.grupo.allfym.ms.ventas.infrastructure.dtos.VentaRequestDto;
import com.grupo.allfym.ms.ventas.infrastructure.dtos.VentaResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*")
public class VentaController {

    private final VentaApplicationService ventaApplicationService;

    public VentaController(VentaApplicationService ventaApplicationService) {
        this.ventaApplicationService = ventaApplicationService;
    }

    @PostMapping
    public ResponseEntity<?> crearVenta(@Valid @RequestBody VentaRequestDto ventaRequest) {
        try {
            System.out.println("Recibiendo la venta: " + ventaRequest);
            
            // Convertir DTO a la entidad de dominio directamente
            Venta venta = VentaRequestDto.toDomainModel(ventaRequest);

            Venta ventaCreada = ventaApplicationService.crearVenta(venta);
            
            // Convertir la respuesta al DTO
            VentaResponseDto response = convertirAResponseDto(ventaCreada);
            
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error al crear venta: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest()
                .body("Error al crear venta: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDto> obtenerVentaPorId(@PathVariable Long id) {
        return ventaApplicationService.buscarPorId(id)
            .map(venta -> new ResponseEntity<>(convertirAResponseDto(venta), HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<VentaResponseDto>> obtenerTodasLasVentas() {
        List<Venta> ventas = ventaApplicationService.obtenerTodasLasVentas();
        List<VentaResponseDto> response = ventas.stream()
                .map(this::convertirAResponseDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<VentaResponseDto>> obtenerVentasPorCliente(@PathVariable Long clienteId) {
        List<Venta> ventas = ventaApplicationService.buscarPorClienteId(clienteId);
        List<VentaResponseDto> response = ventas.stream()
                .map(this::convertirAResponseDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<VentaResponseDto>> obtenerVentasPorEstado(@PathVariable EstadoVenta estado) {
        List<Venta> ventas = ventaApplicationService.buscarPorEstado(estado);
        List<VentaResponseDto> response = ventas.stream()
                .map(this::convertirAResponseDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<VentaResponseDto>> obtenerVentasPorFecha(
            @RequestParam LocalDateTime fechaInicio,
            @RequestParam LocalDateTime fechaFin) {
        List<Venta> ventas = ventaApplicationService.buscarPorFechaRegistro(fechaInicio, fechaFin);
        List<VentaResponseDto> response = ventas.stream()
                .map(this::convertirAResponseDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/confirmar")
    public ResponseEntity<VentaResponseDto> confirmarVenta(@PathVariable Long id) {
        try {
            Venta ventaConfirmada = ventaApplicationService.confirmarVenta(id);
            VentaResponseDto response = convertirAResponseDto(ventaConfirmada);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<VentaResponseDto> cancelarVenta(@PathVariable Long id) {
        try {
            Venta ventaCancelada = ventaApplicationService.cancelarVenta(id);
            VentaResponseDto response = convertirAResponseDto(ventaCancelada);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}/entregar")
    public ResponseEntity<VentaResponseDto> marcarComoEntregada(@PathVariable Long id) {
        try {
            Venta ventaEntregada = ventaApplicationService.marcarComoEntregada(id);
            VentaResponseDto response = convertirAResponseDto(ventaEntregada);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
        try {
            ventaApplicationService.eliminarVenta(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private VentaResponseDto convertirAResponseDto(Venta venta) {
        VentaResponseDto response = new VentaResponseDto();
        response.setId(venta.getId());
        response.setClienteId(venta.getClienteId());
        response.setFechaRegistro(venta.getFechaRegistro().getFechaRegistro());
        response.setMetodoPago(venta.getMetodoPago());
        response.setEstado(venta.getEstado());
        response.setTotal(venta.getTotal());

        // Convertir detalles
        List<VentaResponseDto.DetalleVentaResponseDto> detallesResponse = venta.getDetalles().stream()
                .map(this::convertirDetalleAResponseDto)
                .collect(Collectors.toList());
        response.setDetalles(detallesResponse);

        return response;
    }

    // Convierte el DetalleVenta de dominio a DetalleVentaResponseDto
    private VentaResponseDto.DetalleVentaResponseDto convertirDetalleAResponseDto(DetalleVenta detalle) {
    return new VentaResponseDto.DetalleVentaResponseDto(
        detalle.getId(),
        detalle.getProductoId(),
                detalle.getCantidad(),
                detalle.getPrecioUnitario(),
                detalle.getSubtotal()
        );
    }
}

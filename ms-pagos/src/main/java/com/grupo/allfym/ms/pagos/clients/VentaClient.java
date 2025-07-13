package com.grupo.allfym.ms.pagos.clients;

import com.grupo.allfym.ms.pagos.models.VentaResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ms-ventas", url = "${microservices.ventas.url:http://localhost:8020}")
public interface VentaClient {

    @GetMapping("/api/ventas/{id}")
    ResponseEntity<VentaResponseDTO> obtenerVentaPorId(@PathVariable Long id);

    @GetMapping("/api/ventas")
    ResponseEntity<List<VentaResponseDTO>> obtenerTodasLasVentas();

    @GetMapping("/api/ventas/cliente/{clienteId}")
    ResponseEntity<List<VentaResponseDTO>> obtenerVentasPorCliente(@PathVariable Long clienteId);

    @GetMapping("/api/ventas/estado/{estado}")
    ResponseEntity<List<VentaResponseDTO>> obtenerVentasPorEstado(@PathVariable String estado);

    @PutMapping("/api/ventas/{id}/confirmar")
    ResponseEntity<VentaResponseDTO> confirmarVenta(@PathVariable Long id);

    @PutMapping("/api/ventas/{id}/cancelar")
    ResponseEntity<VentaResponseDTO> cancelarVenta(@PathVariable Long id);
}

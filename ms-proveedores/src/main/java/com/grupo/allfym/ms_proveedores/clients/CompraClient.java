package com.grupo.allfym.ms_proveedores.clients;

import com.grupo.allfym.ms_proveedores.models.dto.CompraResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ms-compra", url = "${microservices.compra.url:http://localhost:8030}")
public interface CompraClient {

    @GetMapping("/api/compras/proveedor/{proveedorId}")
    ResponseEntity<List<CompraResponseDTO>> obtenerComprasPorProveedor(@PathVariable Long proveedorId);

    @GetMapping("/api/compras/{id}")
    ResponseEntity<CompraResponseDTO> obtenerCompraPorId(@PathVariable Long id);

    @GetMapping("/api/compras")
    ResponseEntity<List<CompraResponseDTO>> obtenerComprasPorEstado(@RequestParam String estado);
}

package com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-compra", url = "${microservices.compra.url:http://localhost:8030}")
public interface CompraCliente {

    @GetMapping("/api/compra/{id}")
    ResponseEntity<CompraResponseDTO> obtenerCompraPorId(@PathVariable("id") Long id);

    @GetMapping("/api/compra/proveedor/{idProveedor}")
    ResponseEntity<List<CompraResponseDTO>> obtenerComprasPorProveedor(@PathVariable("idProveedor") Long idProveedor);

    @GetMapping("/api/compra")
    ResponseEntity<List<CompraResponseDTO>> obtenerTodasLasCompras();
}

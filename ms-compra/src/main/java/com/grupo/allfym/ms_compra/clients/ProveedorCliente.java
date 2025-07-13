package com.grupo.allfym.ms_compra.clients;

import com.grupo.allfym.ms_compra.models.dto.ProveedorResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
    name = "ms-proveedores",
    url = "${microservices.proveedores.url:http://localhost:8010}",
    configuration = FeignConfig.class
)
public interface ProveedorCliente {

    @GetMapping("/api/proveedor/{id}")
    ResponseEntity<ProveedorResponseDTO> obtenerProveedorPorId(@PathVariable("id") Long id);

    @GetMapping("/api/proveedor")
    ResponseEntity<List<ProveedorResponseDTO>> obtenerTodosLosProveedores();

    @GetMapping("/api/proveedor/estado/{estado}")
    ResponseEntity<List<ProveedorResponseDTO>> obtenerProveedoresPorEstado(@PathVariable("estado") String estado);

    @GetMapping("/api/proveedor/ruc/{ruc}")
    ResponseEntity<ProveedorResponseDTO> obtenerProveedorPorRuc(@PathVariable("ruc") String ruc);

    @GetMapping("/api/proveedor/estado/ACTIVO")
    ResponseEntity<List<ProveedorResponseDTO>> obtenerProveedoresActivos();
}

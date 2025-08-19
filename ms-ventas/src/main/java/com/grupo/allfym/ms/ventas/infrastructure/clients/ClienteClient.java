package com.grupo.allfym.ms.ventas.infrastructure.clients;

import com.grupo.allfym.ms.ventas.infrastructure.dtos.ClienteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ms-clientes", url = "http://localhost:8020")
public interface ClienteClient {

    @GetMapping("/api/clientes/{id}")
    ResponseEntity<ClienteDto> obtenerClientePorId(@PathVariable Long id);

    @GetMapping("/api/clientes")
    ResponseEntity<List<ClienteDto>> obtenerTodosLosClientes();

    @PutMapping("/api/clientes/{id}/desactivar")
    ResponseEntity<ClienteDto> desactivarCliente(@PathVariable Long id);

    @PutMapping("/api/clientes/{id}/activar")
    ResponseEntity<ClienteDto> activarCliente(@PathVariable Long id);
}

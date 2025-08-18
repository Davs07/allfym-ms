package com.grupo.allfym.ms.ventas.clients;

import com.grupo.allfym.ms.ventas.models.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ms-clientes", url = "http://localhost:8020")
public interface ClienteClient {

    @GetMapping("/api/clientes/{id}")
    ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id);

    @GetMapping("/api/clientes")
    ResponseEntity<List<Cliente>> obtenerTodosLosClientes();

    @PutMapping("/api/clientes/{id}/desactivar")
    ResponseEntity<Cliente> desactivarCliente(@PathVariable Long id);

    @PutMapping("/api/clientes/{id}/activar")
    ResponseEntity<Cliente> activarCliente(@PathVariable Long id);
}

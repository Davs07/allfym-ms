package com.grupo.allfym.ms.ventas.clients;

import com.grupo.allfym.ms.ventas.models.ClienteRequestDTO;
import com.grupo.allfym.ms.ventas.models.ClienteResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ms-clientes", url = "${microservices.clientes.url:http://localhost:8082}")
public interface ClienteClient {

    @PostMapping("/api/clientes")
    ResponseEntity<ClienteResponseDTO> crearCliente(@RequestBody ClienteRequestDTO clienteRequest);

    @GetMapping("/api/clientes/{id}")
    ResponseEntity<ClienteResponseDTO> obtenerClientePorId(@PathVariable Long id);

    @GetMapping("/api/clientes")
    ResponseEntity<List<ClienteResponseDTO>> obtenerTodosLosClientes();

    @GetMapping("/api/clientes/activos")
    ResponseEntity<List<ClienteResponseDTO>> obtenerClientesActivos();

    @GetMapping("/api/clientes/buscar/{nombre}")
    ResponseEntity<List<ClienteResponseDTO>> buscarClientesPorNombre(@PathVariable String nombre);

    @GetMapping("/api/clientes/email/{email}")
    ResponseEntity<ClienteResponseDTO> buscarClientePorEmail(@PathVariable String email);

    @GetMapping("/api/clientes/telefono/{telefono}")
    ResponseEntity<ClienteResponseDTO> buscarClientePorTelefono(@PathVariable String telefono);

    @GetMapping("/api/clientes/dni/{dni}")
    ResponseEntity<ClienteResponseDTO> buscarClientePorDni(@PathVariable String dni);

    @PutMapping("/api/clientes/{id}")
    ResponseEntity<ClienteResponseDTO> actualizarCliente(@PathVariable Long id, @RequestBody ClienteRequestDTO clienteRequest);

    @PutMapping("/api/clientes/{id}/desactivar")
    ResponseEntity<ClienteResponseDTO> desactivarCliente(@PathVariable Long id);

    @PutMapping("/api/clientes/{id}/activar")
    ResponseEntity<ClienteResponseDTO> activarCliente(@PathVariable Long id);
}

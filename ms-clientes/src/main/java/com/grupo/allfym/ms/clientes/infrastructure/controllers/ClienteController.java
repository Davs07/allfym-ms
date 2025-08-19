package com.grupo.allfym.ms.clientes.infrastructure.controllers;

import com.grupo.allfym.ms.clientes.application.services.ClienteApplicationService;
import com.grupo.allfym.ms.clientes.domain.models.entities.Cliente;
import com.grupo.allfym.ms.clientes.domain.models.ov.EmailAddress;
import com.grupo.allfym.ms.clientes.domain.models.ov.Telefono;
import com.grupo.allfym.ms.clientes.infrastructure.dtos.ClienteRequestDto;
import com.grupo.allfym.ms.clientes.infrastructure.dtos.ClienteResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST súper simple para clientes
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteApplicationService clienteApplicationService;

    public ClienteController(ClienteApplicationService clienteApplicationService) {
        this.clienteApplicationService = clienteApplicationService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDto> crear(@RequestBody ClienteRequestDto request) {
        try {
            Cliente cliente = clienteApplicationService.crear(
                    request.getNombre(),
                    request.getApellido(),
                    request.getDni(),
                    new EmailAddress(request.getEmail()),
                    new Telefono(request.getTelefono()),
                    request.getDireccion()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDto(cliente));
        } catch (Exception e) {
            throw new RuntimeException("Error al crear cliente: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> obtenerPorId(@PathVariable Long id) {
        return clienteApplicationService.obtenerPorId(id)
                .map(cliente -> ResponseEntity.ok(toResponseDto(cliente)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> listarTodos() {
        List<Cliente> clientes = clienteApplicationService.listarTodos();
        List<ClienteResponseDto> response = clientes.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/activos")
    public ResponseEntity<List<ClienteResponseDto>> listarActivos() {
        List<Cliente> clientes = clienteApplicationService.listarActivos();
        List<ClienteResponseDto> response = clientes.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> actualizar(@PathVariable Long id, @RequestBody ClienteRequestDto request) {
        try {
            Cliente cliente = clienteApplicationService.actualizar(id, request.getNombre(), request.getApellido(), request.getDireccion());
            return ResponseEntity.ok(toResponseDto(cliente));
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar cliente: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/email")
    public ResponseEntity<ClienteResponseDto> cambiarEmail(@PathVariable Long id, @RequestParam String email) {
        try {
            Cliente cliente = clienteApplicationService.cambiarEmail(id, new EmailAddress(email));
            return ResponseEntity.ok(toResponseDto(cliente));
        } catch (Exception e) {
            throw new RuntimeException("Error al cambiar email: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/telefono")
    public ResponseEntity<ClienteResponseDto> cambiarTelefono(@PathVariable Long id, @RequestParam String telefono) {
        try {
            Cliente cliente = clienteApplicationService.cambiarTelefono(id, new Telefono(telefono));
            return ResponseEntity.ok(toResponseDto(cliente));
        } catch (Exception e) {
            throw new RuntimeException("Error al cambiar teléfono: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/activar")
    public ResponseEntity<ClienteResponseDto> activar(@PathVariable Long id) {
        try {
            Cliente cliente = clienteApplicationService.activar(id);
            return ResponseEntity.ok(toResponseDto(cliente));
        } catch (Exception e) {
            throw new RuntimeException("Error al activar cliente: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/desactivar")
    public ResponseEntity<ClienteResponseDto> desactivar(@PathVariable Long id) {
        try {
            Cliente cliente = clienteApplicationService.desactivar(id);
            return ResponseEntity.ok(toResponseDto(cliente));
        } catch (Exception e) {
            throw new RuntimeException("Error al desactivar cliente: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            clienteApplicationService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar cliente: " + e.getMessage());
        }
    }

    // Mapper súper simple - Maneja valores nulos
    private ClienteResponseDto toResponseDto(Cliente cliente) {
        return new ClienteResponseDto(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getDni(),
                cliente.getEmail() != null ? cliente.getEmail().getValor() : null,
                cliente.getTelefono() != null ? cliente.getTelefono().getNumero() : null,
                cliente.getDireccion(),
                cliente.getEstado() != null ? cliente.getEstado().name() : null,
                cliente.getFechaRegistro() != null ? cliente.getFechaRegistro().getFechaRegistro() : null
        );
    }
}

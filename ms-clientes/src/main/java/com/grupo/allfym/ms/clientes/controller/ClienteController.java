package com.grupo.allfym.ms.clientes.controller;

import com.grupo.allfym.ms.clientes.models.ClienteRequestDTO;
import com.grupo.allfym.ms.clientes.models.ClienteResponseDTO;
import com.grupo.allfym.ms.clientes.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> crearCliente(@Valid @RequestBody ClienteRequestDTO clienteRequest) {
        try {
            ClienteResponseDTO clienteCreado = clienteService.agregarCliente(clienteRequest);
            return new ResponseEntity<>(clienteCreado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> obtenerClientePorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id)
            .map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> obtenerTodosLosClientes() {
        List<ClienteResponseDTO> clientes = clienteService.obtenerTodosLosClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/activos")
    public ResponseEntity<List<ClienteResponseDTO>> obtenerClientesActivos() {
        List<ClienteResponseDTO> clientes = clienteService.obtenerClientesActivos();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<ClienteResponseDTO>> buscarClientesPorNombre(@PathVariable String nombre) {
        List<ClienteResponseDTO> clientes = clienteService.buscarPorNombre(nombre);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ClienteResponseDTO> buscarClientePorEmail(@PathVariable String email) {
        return clienteService.buscarPorEmail(email)
            .map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/telefono/{telefono}")
    public ResponseEntity<ClienteResponseDTO> buscarClientePorTelefono(@PathVariable String telefono) {
        return clienteService.buscarPorTelefono(telefono)
            .map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<ClienteResponseDTO> buscarClientePorDni(@PathVariable String dni) {
        return clienteService.buscarPorDni(dni)
            .map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> actualizarCliente(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequestDTO clienteRequest) {
        try {
            ClienteResponseDTO clienteActualizado = clienteService.actualizarCliente(id, clienteRequest);
            return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/desactivar")
    public ResponseEntity<ClienteResponseDTO> desactivarCliente(@PathVariable Long id) {
        try {
            ClienteResponseDTO clienteDesactivado = clienteService.desactivarCliente(id);
            return new ResponseEntity<>(clienteDesactivado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/activar")
    public ResponseEntity<ClienteResponseDTO> activarCliente(@PathVariable Long id) {
        try {
            ClienteResponseDTO clienteActivado = clienteService.activarCliente(id);
            return new ResponseEntity<>(clienteActivado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}

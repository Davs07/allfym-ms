package com.grupo.allfym.ms.clientes.controller;

import com.grupo.allfym.ms.clientes.entity.Cliente;
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
    public ResponseEntity<Cliente> crearCliente(@Valid @RequestBody Cliente clienteRequest) {
        try {
            Cliente clienteCreado = clienteService.agregarCliente(clienteRequest);
            return new ResponseEntity<>(clienteCreado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id)
            .map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Cliente>> obtenerClientesActivos() {
        List<Cliente> clientes = clienteService.obtenerClientesActivos();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<Cliente>> buscarClientesPorNombre(@PathVariable String nombre) {
        List<Cliente> clientes = clienteService.buscarPorNombre(nombre);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Cliente> buscarClientePorEmail(@PathVariable String email) {
        return clienteService.buscarPorEmail(email)
            .map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/telefono/{telefono}")
    public ResponseEntity<Cliente> buscarClientePorTelefono(@PathVariable String telefono) {
        return clienteService.buscarPorTelefono(telefono)
            .map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<Cliente> buscarClientePorDni(@PathVariable String dni) {
        return clienteService.buscarPorDni(dni)
            .map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(
            @PathVariable Long id,
            @Valid @RequestBody Cliente clienteRequest) {
        try {
            Cliente clienteActualizado = clienteService.actualizarCliente(id, clienteRequest);
            return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/desactivar")
    public ResponseEntity<Cliente> desactivarCliente(@PathVariable Long id) {
        try {
            Cliente clienteDesactivado = clienteService.desactivarCliente(id);
            return new ResponseEntity<>(clienteDesactivado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/activar")
    public ResponseEntity<Cliente> activarCliente(@PathVariable Long id) {
        try {
            Cliente clienteActivado = clienteService.activarCliente(id);
            return new ResponseEntity<>(clienteActivado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}

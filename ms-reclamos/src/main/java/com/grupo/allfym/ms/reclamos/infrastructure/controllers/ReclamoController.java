package com.grupo.allfym.ms.reclamos.infrastructure.controllers;

import com.grupo.allfym.ms.reclamos.application.services.ReclamoService;
import com.grupo.allfym.ms.reclamos.domain.models.Cliente;
import com.grupo.allfym.ms.reclamos.domain.models.classes.Reclamo;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ReclamoCliente")
@AllArgsConstructor
public class ReclamoController {

    @Autowired
    private final ReclamoService service;

    @PostMapping
    public ResponseEntity<?> crearReclamo(@RequestBody Reclamo reclamo) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(service.guardar(reclamo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalleReclamo(@PathVariable Long id) {
        Optional<Reclamo> op = service.detalleReclamo(id);
        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Reclamo> listaReclamo() {
        return service.listaReclamo();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarReclamo(@PathVariable Long id, @RequestBody Reclamo reclamo) {
        Optional<Reclamo> op = service.detalleReclamo(id);
        if (op.isPresent()) {
            reclamo.setIdReclamo(id);
            Optional<Reclamo> update = service.actualizarReclamo(id,reclamo);
            return ResponseEntity.ok(update.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTask(@PathVariable Long id) {
        Optional<Reclamo> op = service.detalleReclamo(id);
        if (op.isPresent()) {
            service.eliminarReclamo(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscarCliente/{nombre}")
    public List<Reclamo> buscarPorCLiente(@PathVariable String nombre) {
        return service.buscarCliente(nombre);
    }

    //Accesos remotos
    @PutMapping("/asignarCliente/{reclamoid}")
    public ResponseEntity<?> asignarCliente (@RequestBody Cliente cliente, @PathVariable Long reclamoid) {
        Optional<Cliente> op;
        try {
            op = service.asignarCliente(cliente,reclamoid);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("Mensaje","No existe el cliente por el id " + cliente.getId() +
                            " o hubo error en la comunicación"+e.getMessage()));
        }

        if (op.isPresent())
            return ResponseEntity.status(HttpStatus.CREATED).body(op.get());
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/removerCliente/{reclamoid}")
    public ResponseEntity<?> removerCliente (@RequestBody Cliente cliente, @PathVariable Long reclamoid) {
        Optional<Cliente> op;
        try {
            op = service.removerCliente(cliente,reclamoid);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("Mensaje","No existe el cliente por el id " + cliente.getId() +
                            " o hubo error en la comunicación"+e.getMessage()));
        }

        if (op.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(op.get());
        return ResponseEntity.notFound().build();
    }
}

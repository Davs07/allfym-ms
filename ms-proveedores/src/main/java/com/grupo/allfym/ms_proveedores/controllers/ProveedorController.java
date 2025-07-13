package com.grupo.allfym.ms_proveedores.controllers;

import com.grupo.allfym.ms_proveedores.models.entities.Proveedor;
import com.grupo.allfym.ms_proveedores.models.enums.Estado;
import com.grupo.allfym.ms_proveedores.services.ProveedorServicio;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorServicio servicio;

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(servicio.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<Proveedor> op = servicio.buscarPorId(id);

        if(op.isPresent()){
            return ResponseEntity.ok(op.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Proveedor>> listarPorEstado(@PathVariable String estado) {
        try {
            Estado estadoEnum = Estado.valueOf(estado.toUpperCase());
            List<Proveedor> proveedores = servicio.listaporEstado(estadoEnum);
            return ResponseEntity.ok(proveedores);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody Proveedor prove){
        Proveedor proveedor = servicio.guardar(prove);
        return ResponseEntity.status(HttpStatus.CREATED).body(prove);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> guardar(@RequestBody Proveedor prove,@PathVariable Long id){
        Optional<Proveedor> op = servicio.buscarPorId(id);
        if(op.isPresent()){
            Proveedor proveedor = op.get();
            proveedor.setNombre(prove.getNombre());
            proveedor.setEstado(prove.getEstado());
            proveedor.setDireccion(prove.getDireccion());
            proveedor.setEmail(prove.getEmail());
            proveedor.setRUC(prove.getRUC());
            proveedor.setFechaDeRegistro(prove.getFechaDeRegistro());
            proveedor.setTelefono(prove.getTelefono());

            return ResponseEntity.status(HttpStatus.OK).body(proveedor);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Optional<Proveedor> op = servicio.buscarPorId(id);
        if (op.isPresent()) {
            servicio.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

package com.grupo.allfym.ms_proveedores.infrastructure.controllers;

import com.grupo.allfym.ms_proveedores.application.services.ProveedorService;
import com.grupo.allfym.ms_proveedores.domain.models.entities.Proveedor;
import com.grupo.allfym.ms_proveedores.domain.models.enums.Estado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {

    private ProveedorService servicio;


    public ProveedorController(ProveedorService servicio) {
        this.servicio = servicio;
    }

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
            List<Proveedor> proveedores = servicio.listarPorEstado(estadoEnum);
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
//
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> guardar(@RequestBody Proveedor prove,@PathVariable Long id){
        try{
            Proveedor proveedor = servicio.actualizar(id,prove);
            return ResponseEntity.ok(proveedor);
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
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

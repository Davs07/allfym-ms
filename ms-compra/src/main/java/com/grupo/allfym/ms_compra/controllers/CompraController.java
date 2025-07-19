package com.grupo.allfym.ms_compra.controllers;

import com.grupo.allfym.ms_compra.models.CambiarEstadoNuevo;
import com.grupo.allfym.ms_compra.models.entities.Compra;
import com.grupo.allfym.ms_compra.models.entities.DetalleCompra;
import com.grupo.allfym.ms_compra.models.enums.Estado;
import com.grupo.allfym.ms_compra.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/compra")
public class CompraController {
    @Autowired
    private CompraService compraService;

    @GetMapping
    public ResponseEntity<List<Compra>> listar() {
        return ResponseEntity.ok(compraService.listar());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<Compra> op = compraService.buscarPorId(id);

        if(op.isPresent()){
            Compra compra = op.get();
            return ResponseEntity.status(HttpStatus.OK).body(compra);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Compra>> buscarPorEstado(@PathVariable String estado) {
        Estado estadoEnum = Estado.valueOf(estado.toUpperCase());
        return ResponseEntity.ok(compraService.buscarPorEstado(estadoEnum));
    }

    @GetMapping("/proveedor/{idProveedor}")
    public ResponseEntity<List<Compra>> buscarPorIdProveedor(@PathVariable Long idProveedor) {
        return ResponseEntity.ok(compraService.buscarPorIdProveedor(idProveedor));
    }

    @GetMapping("/proveedor/nombre/{nombre}")
    public ResponseEntity<List<Compra>> buscarPorNombreProveedor(@PathVariable String nombre) {
        return ResponseEntity.ok(compraService.buscarPorNombreProveedor(nombre));
    }

    @PostMapping
    public ResponseEntity<Compra> guardar(@RequestBody Compra compra) {
        Compra guardada = compraService.guardar(compra);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
    }

    @PutMapping("/actualizarEstado/{id}")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long id, @RequestBody CambiarEstadoNuevo nuevoEstado) {
        try {
            compraService.cambiarEstado(id, nuevoEstado.getEstado().toString());
            return ResponseEntity.ok("Estado actualizado");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminarCompra (@PathVariable Long id){
        Optional<Compra> op = compraService.buscarPorId(id);
        if(op.isPresent()){
            compraService.eliminar(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/detalle/{id}")
    public ResponseEntity<Compra> agregarDetalle(@RequestBody DetalleCompra detalleCompra,@PathVariable Long id) {
        try {
            Compra compraActualizada = compraService.agregarDetalle(id, detalleCompra);
            return ResponseEntity.ok(compraActualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

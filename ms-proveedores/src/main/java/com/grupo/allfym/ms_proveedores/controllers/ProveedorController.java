package com.grupo.allfym.ms_proveedores.controllers;

import com.grupo.allfym.ms_proveedores.models.dto.ProveedorRequestDTO;
import com.grupo.allfym.ms_proveedores.models.dto.ProveedorResponseDTO;
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

    // === ENDPOINTS CON DTOs (Para comunicación entre microservicios) ===

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResponseDTO> detalle(@PathVariable Long id){
        Optional<ProveedorResponseDTO> proveedor = servicio.obtenerProveedorPorId(id);
        return proveedor.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProveedorResponseDTO>> listar(){
        List<ProveedorResponseDTO> proveedores = servicio.obtenerTodosLosProveedores();
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ProveedorResponseDTO>> listarPorEstado(@PathVariable String estado) {
        try {
            List<ProveedorResponseDTO> proveedores = servicio.obtenerProveedoresPorEstado(estado);
            return ResponseEntity.ok(proveedores);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ruc/{ruc}")
    public ResponseEntity<ProveedorResponseDTO> buscarPorRuc(@PathVariable String ruc) {
        Optional<ProveedorResponseDTO> proveedor = servicio.buscarPorRuc(ruc);
        return proveedor.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProveedorResponseDTO> crear(@RequestBody ProveedorRequestDTO proveedorRequest){
        try {
            ProveedorResponseDTO proveedor = servicio.crearProveedor(proveedorRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(proveedor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorResponseDTO> actualizar(@PathVariable Long id, @RequestBody ProveedorRequestDTO proveedorRequest){
        try {
            ProveedorResponseDTO proveedor = servicio.actualizarProveedor(id, proveedorRequest);
            return ResponseEntity.ok(proveedor);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            servicio.eliminarProveedor(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // === ENDPOINTS LEGACY (Para compatibilidad hacia atrás) ===

    @GetMapping("/legacy")
    public ResponseEntity<List<Proveedor>> listarLegacy(){
        return ResponseEntity.ok(servicio.listar());
    }

    @GetMapping("/legacy/{id}")
    public ResponseEntity<Proveedor> detalleLegacy(@PathVariable Long id){
        Optional<Proveedor> op = servicio.buscarPorId(id);
        return op.map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/guardar")
    public ResponseEntity<Proveedor> guardar(@RequestBody Proveedor prove){
        Proveedor proveedor = servicio.guardar(prove);
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedor);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Proveedor> actualizarLegacy(@RequestBody Proveedor prove, @PathVariable Long id){
        Optional<Proveedor> op = servicio.buscarPorId(id);
        if(op.isPresent()){
            Proveedor proveedor = op.get();
            proveedor.setNombre(prove.getNombre());
            proveedor.setEstado(prove.getEstado());
            proveedor.setDireccion(prove.getDireccion());
            proveedor.setEmail(prove.getEmail());
            proveedor.setRUC(prove.getRUC());
            proveedor.setFechaRegistro(prove.getFechaRegistro());
            proveedor.setTelefono(prove.getTelefono());

            Proveedor proveedorActualizado = servicio.guardar(proveedor);
            return ResponseEntity.status(HttpStatus.OK).body(proveedorActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/legacy/estado/{estado}")
    public ResponseEntity<List<Proveedor>> listarPorEstadoLegacy(@PathVariable String estado) {
        try {
            Estado estadoEnum = Estado.valueOf(estado.toUpperCase());
            List<Proveedor> proveedores = servicio.listaporEstado(estadoEnum);
            return ResponseEntity.ok(proveedores);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

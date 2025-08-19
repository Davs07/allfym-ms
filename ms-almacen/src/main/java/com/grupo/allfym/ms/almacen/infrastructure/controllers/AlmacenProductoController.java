package com.grupo.allfym.ms.almacen.infrastructure.controllers;

import com.grupo.allfym.ms.almacen.application.services.AlmacenProductoService;
import com.grupo.allfym.ms.almacen.domain.models.AlmacenProducto;
import com.grupo.allfym.ms.almacen.domain.models.Movimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/almacen")
public class AlmacenProductoController {

    @Autowired
    AlmacenProductoService almacenProductoService;

    @PostMapping("/producto/{idProducto}/agregar-stock/{stockInicial}")
    public ResponseEntity<?> agregarProductoAlmacen(@PathVariable Long idProducto, @PathVariable Integer stockInicial) {
        try {
            AlmacenProducto almacenProducto = almacenProductoService.createAlmacenProducto(idProducto, stockInicial);
            return ResponseEntity.status(HttpStatus.CREATED).body(almacenProducto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/producto/{idAlmacen}/aumentar-stock/{cantidad}")
    public ResponseEntity<?> aumentarStock(@PathVariable Long idAlmacen, @PathVariable Integer cantidad) {
        try {
            AlmacenProducto almacenProducto = almacenProductoService.aumentarStock(idAlmacen, cantidad);
            return ResponseEntity.ok(almacenProducto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/producto/{idAlmacen}/reducir-stock/{cantidad}")
    public ResponseEntity<?> reducirStock(@PathVariable Long idAlmacen, @PathVariable Integer cantidad) {
        try {
            AlmacenProducto almacenProducto = almacenProductoService.reducirStock(idAlmacen, cantidad);
            return ResponseEntity.ok(almacenProducto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/producto/{idProducto}/historial-movimientos")
    public ResponseEntity<List<Movimiento>> obtenerHistorialMovimientos(@PathVariable Long idProducto) {
        List<Movimiento> movimientos = almacenProductoService.obtenerHistorialMovimientos(idProducto);
        return ResponseEntity.ok(movimientos);
    }

    @GetMapping("/productos")
    public ResponseEntity<List<AlmacenProducto>> listarProductosSimple() {
        List<AlmacenProducto> productos = almacenProductoService.getAllAlmacenProducts();
        return ResponseEntity.ok(productos);
    }

    @DeleteMapping("/producto/{idAlmacen}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long idAlmacen) {
        try {
            almacenProductoService.deleteAlmacenById(idAlmacen);
            return ResponseEntity.ok("Producto eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

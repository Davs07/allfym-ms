package com.grupo.allfym.ms.almacen.controller;

import com.grupo.allfym.ms.almacen.models.Producto;
import com.grupo.allfym.ms.almacen.models.entity.AlmacenProducto;
import com.grupo.allfym.ms.almacen.models.entity.Movimiento;
import com.grupo.allfym.ms.almacen.service.AlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/almacen")
public class AlmacenController {

    @Autowired
    private AlmacenService almacenService;

    @PostMapping("/producto/{idProducto}/agregar-stock/{stockInicial}")
    public ResponseEntity<?> agregarProductoAlmacen(@PathVariable Long idProducto, @PathVariable Integer stockInicial) {
        try {
            AlmacenProducto almacenProducto = almacenService.agregarProductoAlmacen(idProducto, stockInicial);
            return ResponseEntity.status(HttpStatus.CREATED).body(almacenProducto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PutMapping("/producto/{idProducto}/aumentar-stock/{cantidad}")
    public ResponseEntity<?> aumentarStock(@PathVariable Long idProducto, @PathVariable Integer cantidad) {
        try {
            AlmacenProducto almacenProducto = almacenService.aumentarStock(idProducto, cantidad);
            return ResponseEntity.ok(almacenProducto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/producto/{idProducto}/reducir-stock/{cantidad}")
    public ResponseEntity<?> reducirStock(@PathVariable Long idProducto, @PathVariable Integer cantidad) {
        try {
            AlmacenProducto almacenProducto = almacenService.reducirStock(idProducto, cantidad);
            return ResponseEntity.ok(almacenProducto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("/producto/{idProducto}/historial-movimientos")

    public ResponseEntity<List<Movimiento>> obtenerHistorialMovimientos(@PathVariable Long idProducto) {
        List<Movimiento> movimientos = almacenService.obtenerHistorialMovimientos(idProducto);
        return ResponseEntity.ok(movimientos);
    }

    @GetMapping("/productos")
    public ResponseEntity<?> listarProductosEnAlmacen() {
        try {
            List<Map<String, Object>> productosConStock = almacenService.listarProductosConDetalles();
            return ResponseEntity.ok(productosConStock);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener productos del almacén: " + e.getMessage());
        }
    }
    @GetMapping("/productos/simple")
    public ResponseEntity<List<AlmacenProducto>> listarProductosSimple() {
        List<AlmacenProducto> productos = almacenService.listarProductosEnAlmacen();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/producto/{idProducto}/detalle")
    public ResponseEntity<?> obtenerDetalleProducto(@PathVariable Long idProducto) {
        try {
            Producto producto = almacenService.obtenerDetalleProducto(idProducto);
            return ResponseEntity.ok(producto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/producto/{idProducto}/stock")
    public ResponseEntity<?> consultarStock(@PathVariable Long idProducto) {
        try {
            Integer stock = almacenService.consultarStock(idProducto);
            return ResponseEntity.ok(Map.of("idProducto", idProducto, "stock", stock));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
package com.grupo.allfym.ms.productos.infrastructure.controllers;


import com.grupo.allfym.ms.productos.application.services.ProductoService;
import com.grupo.allfym.ms.productos.domain.models.Producto;
import com.grupo.allfym.ms.productos.infrastructure.entities.Categoria;
import com.grupo.allfym.ms.productos.infrastructure.entities.Marca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @PostMapping("")
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.createProduct(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    @GetMapping()
    public List<Producto> listarProductos() {
        return productoService.getAllProducts();
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable Long id) {
        Optional<Producto> producto = productoService.getProductById(id);
        return producto.isPresent() ? ResponseEntity.ok(producto.get()) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado con ID: " + id);
    }

    @GetMapping("/buscar/categoria/{categoria}")
    public ResponseEntity<List<Producto>> obtenerProductoPorCategoria(@PathVariable Categoria categoria) {
        List<Producto> productos = productoService.findByCategoria(categoria);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/buscar/marca/{marca}")
    public ResponseEntity<List<Producto>> obtenerProductoPorMarca(@PathVariable Marca marca) {
        List<Producto> productos = productoService.findByMarca(marca);
        return ResponseEntity.ok(productos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        try {
            Optional<Producto> productoExistente = productoService.getProductById(id);
            if (productoExistente.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado con ID: " + id);
            }
            producto.setId(id);
            Producto productoActualizado = productoService.createProduct(producto);
            return ResponseEntity.ok(productoActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar producto: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        try {
            Optional<Producto> producto = productoService.getProductById(id);
            if (producto.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado con ID: " + id);
            }
            productoService.deleteProductoById(id);
            return ResponseEntity.ok("Producto eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al eliminar producto: " + e.getMessage());
        }
    }
}

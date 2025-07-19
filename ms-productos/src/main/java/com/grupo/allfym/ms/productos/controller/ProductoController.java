package com.grupo.allfym.ms.productos.controller;

import com.grupo.allfym.ms.productos.Service.ProductoService;
import com.grupo.allfym.ms.productos.models.entity.Categoria;
import com.grupo.allfym.ms.productos.models.entity.Marca;
import com.grupo.allfym.ms.productos.models.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("")
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.guardarProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    @GetMapping()
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable Long id) {
        Optional<Producto> producto = productoService.obtenerProductoPorId(id);
        return producto.isPresent() ? ResponseEntity.ok(producto.get()) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado con ID: " + id);
    }

    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<?> obtenerProductoPorNombre(@PathVariable String nombre) {
        Optional<Producto> producto = productoService.obtenerProductoPorNombre(nombre);
        return producto.isPresent() ? ResponseEntity.ok(producto.get()) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado con nombre: " + nombre);
    }

    @GetMapping("/buscar/categoria/{categoria}")
    public ResponseEntity<List<Producto>> obtenerProductoPorCategoria(@PathVariable Categoria categoria) {
        List<Producto> productos = productoService.obtenerProductosPorCategoria(categoria);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/buscar/marca/{marca}")
    public ResponseEntity<List<Producto>> obtenerProductoPorMarca(@PathVariable Marca marca) {
        List<Producto> productos = productoService.obtenerProductosPorMarca(marca);
        return ResponseEntity.ok(productos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        try {
            Optional<Producto> productoExistente = productoService.obtenerProductoPorId(id);
            if (productoExistente.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado con ID: " + id);
            }
            producto.setIdProducto(id);
            Producto productoActualizado = productoService.guardarProducto(producto);
            return ResponseEntity.ok(productoActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar producto: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        try {
            Optional<Producto> producto = productoService.obtenerProductoPorId(id);
            if (producto.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado con ID: " + id);
            }
            productoService.eliminarProducto(id);
            return ResponseEntity.ok("Producto eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al eliminar producto: " + e.getMessage());
        }
    }
}

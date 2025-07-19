package com.grupo.allfym.ms.ventas.clients;

import com.grupo.allfym.ms.ventas.models.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-productos", url = "http://localhost:8070")
public interface ProductoClient {

    @GetMapping("/api/productos/buscar/{id}")
    ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id);

    @GetMapping("/api/productos")
    ResponseEntity<List<?>> obtenerTodosLosProductos();

    @GetMapping("/api/productos/buscar/nombre/{nombre}")
    ResponseEntity<?> obtenerProductoPorNombre(@PathVariable String nombre);

    @GetMapping("/api/productos/buscar/categoria/{categoria}")
    ResponseEntity<List<?>> obtenerProductosPorCategoria(@PathVariable String categoria);
}

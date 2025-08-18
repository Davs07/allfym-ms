package com.grupo.allfym.ms.ventas.infrastructure.clients;

import com.grupo.allfym.ms.ventas.infrastructure.dtos.ProductoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Cliente Feign para comunicación con el microservicio de productos.
 */
@FeignClient(name = "ms-productos", url = "http://localhost:8050")
public interface ProductoClient {

    @GetMapping("/api/productos/buscar/{id}")
    ResponseEntity<ProductoDto> obtenerProductoPorId(@PathVariable Long id);

    @GetMapping("/api/productos")
    ResponseEntity<List<ProductoDto>> obtenerTodosLosProductos();

    @GetMapping("/api/productos/buscar/nombre/{nombre}")
    ResponseEntity<ProductoDto> obtenerProductoPorNombre(@PathVariable String nombre);

    @GetMapping("/api/productos/buscar/categoria/{categoria}")
    ResponseEntity<List<ProductoDto>> obtenerProductosPorCategoria(@PathVariable String categoria);
}

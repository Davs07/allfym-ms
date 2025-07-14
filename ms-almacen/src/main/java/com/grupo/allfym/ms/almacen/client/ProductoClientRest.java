package com.grupo.allfym.ms.almacen.client;

import com.grupo.allfym.ms.almacen.models.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-productos", url = "${microservices.productos.url:http://localhost:8070}")
public interface ProductoClientRest {

    @GetMapping("/api/productos/buscar/{id}")
    Producto obtenerProductoPorId(@PathVariable Long id);
}
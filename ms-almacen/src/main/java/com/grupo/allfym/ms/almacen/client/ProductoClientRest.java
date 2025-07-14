package com.grupo.allfym.ms.almacen.client;

import com.grupo.allfym.ms.almacen.models.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-productos", url = "localhost:8030/api/productos")
public interface ProductoClientRest {

    @GetMapping("/buscar/{id}")
    Producto detalle(@PathVariable Long id);

    @PostMapping("/crear")
    Producto crear(@RequestBody Producto producto);
}
package com.grupo.allfym.ms.ventas.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-almacen", url = "http://localhost:8010/api/almacen")
public interface AlmacenClient {

    @PutMapping("/producto/{idProducto}/reducir-stock/{cantidad}")
    void reducirStock(@PathVariable("idProducto") Long productoId, @PathVariable("cantidad") Integer cantidad);
}
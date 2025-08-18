package com.grupo.allfym.ms.ventas.infrastructure.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * Cliente Feign para comunicación con el microservicio de almacén.
 */
@FeignClient(name = "ms-almacen", url = "http://localhost:8010/api/almacen")
public interface AlmacenClient {

    @PutMapping("/producto/{idProducto}/reducir-stock/{cantidad}")
    void reducirStock(@PathVariable("idProducto") String producto, @PathVariable("cantidad") Integer cantidad);
}

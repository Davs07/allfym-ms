package com.grupo.allfym.ms_compra.infrastucture.clients;

import com.grupo.allfym.ms_compra.infrastucture.dtos.ProductoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "ms-almacen", url = "http://localhost:8010/api/almacen")
public interface AlmacenClient {
    @PutMapping("/producto/{id}/aumentar-stock/{cantidad}")
    void aumentarStock(@PathVariable("id") Long productoId, @PathVariable("cantidad") Integer cantidad);

    @GetMapping("/productos/simple")
    List<ProductoDto> obtenerProductos();
}

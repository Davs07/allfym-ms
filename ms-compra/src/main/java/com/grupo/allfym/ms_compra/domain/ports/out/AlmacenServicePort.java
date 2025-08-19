package com.grupo.allfym.ms_compra.domain.ports.out;

import com.grupo.allfym.ms.compra.domain.models.Producto;

import java.util.List;

public interface AlmacenServicePort {
    List<Producto> obtenerProductos();
    void aumentarStock(Long productoId, Integer cantidad);
}

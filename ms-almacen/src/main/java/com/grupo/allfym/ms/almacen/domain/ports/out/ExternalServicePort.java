package com.grupo.allfym.ms.almacen.domain.ports.out;

import com.grupo.allfym.ms.almacen.infrastructure.entities.pojo.Producto;

public interface ExternalServicePort {
    Producto getProductoById(Long idProducto);
}

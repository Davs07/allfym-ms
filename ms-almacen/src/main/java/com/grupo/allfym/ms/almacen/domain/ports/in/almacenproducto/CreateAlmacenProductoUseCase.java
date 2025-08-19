package com.grupo.allfym.ms.almacen.domain.ports.in.almacenproducto;

import com.grupo.allfym.ms.almacen.domain.models.AlmacenProducto;

public interface CreateAlmacenProductoUseCase {
    AlmacenProducto createAlmacenProducto(Long idProducto, Integer stockInicial) throws Exception;
}

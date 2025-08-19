package com.grupo.allfym.ms.almacen.domain.ports.in.almacenproducto;

import com.grupo.allfym.ms.almacen.domain.models.AlmacenProducto;

public interface ReducirStockAlmacenProductoUseCase {
    AlmacenProducto reducirStock(Long idAlmacen, Integer cantidad);
}

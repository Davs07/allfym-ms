package com.grupo.allfym.ms.almacen.domain.ports.in.almacenproducto;

import com.grupo.allfym.ms.almacen.domain.models.AlmacenProducto;

public interface AumentarStockAlmacenProductoUseCase {
    AlmacenProducto aumentarStock(Long idAlmacen, Integer cantidad);
}

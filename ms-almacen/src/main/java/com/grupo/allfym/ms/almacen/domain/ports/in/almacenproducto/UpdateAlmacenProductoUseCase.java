package com.grupo.allfym.ms.almacen.domain.ports.in.almacenproducto;

import com.grupo.allfym.ms.almacen.domain.models.AlmacenProducto;

import java.util.Optional;

public interface UpdateAlmacenProductoUseCase {

    Optional<AlmacenProducto> updateProducto(AlmacenProducto almacenProductoActualizado, Long idProducto);
}

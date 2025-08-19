package com.grupo.allfym.ms.almacen.domain.ports.in.almacenproducto;

import com.grupo.allfym.ms.almacen.domain.models.AlmacenProducto;

import java.util.List;
import java.util.Optional;

public interface RetrieveAlmacenProductoUseCase {
    Optional<AlmacenProducto> getAlmacenProductById(Long id);
    List<AlmacenProducto> getAllAlmacenProducts();
}

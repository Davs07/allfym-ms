package com.grupo.allfym.ms.almacen.domain.ports.out;

import com.grupo.allfym.ms.almacen.domain.models.AlmacenProducto;

import java.util.List;
import java.util.Optional;

public interface AlmacenProductoRepositoryPort {
    AlmacenProducto save(AlmacenProducto almacenProducto);
    List<AlmacenProducto> findAll();
    Optional<AlmacenProducto> findByIdAlmacen(Long idAlmacen);
    Optional<AlmacenProducto> findByIdProducto(Long idProducto); //
    Optional<AlmacenProducto> update(AlmacenProducto almacenProducto);
    boolean deleteByIdAlmacen(Long idAlmacen);
}


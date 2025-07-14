package com.grupo.allfym.ms.almacen.repository;

import com.grupo.allfym.ms.almacen.models.entity.AlmacenProducto;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AlmacenProductoRepository extends CrudRepository<AlmacenProducto, Long> {

    Optional<AlmacenProducto> findByIdProducto(Long idProducto);
}
package com.grupo.allfym.ms.almacen.repository;

import com.grupo.allfym.ms.almacen.models.entity.Movimiento;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovimientoRepository extends CrudRepository<Movimiento, Long> {

    List<Movimiento> findByAlmacenProductoIdProducto(Long idProducto);
}
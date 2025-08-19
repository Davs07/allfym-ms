package com.grupo.allfym.ms.almacen.domain.ports.out;

import com.grupo.allfym.ms.almacen.domain.models.Movimiento;
import com.grupo.allfym.ms.almacen.infrastructure.entities.TipoMovimiento;

import java.util.List;
import java.util.Optional;

public interface MovimientoRepositoryPort {
    Movimiento save(Movimiento movimiento);
    List<Movimiento> findAll();
    Optional<Movimiento> findById(Long id);
    List<Movimiento> findByTipoMovimiento(TipoMovimiento tipoMovimiento);
    Optional<Movimiento> update(Movimiento movimiento);
    boolean deleteById(Long id);
}

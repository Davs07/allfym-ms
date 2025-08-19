package com.grupo.allfym.ms.almacen.domain.ports.in.movimiento;

import com.grupo.allfym.ms.almacen.domain.models.Movimiento;
import com.grupo.allfym.ms.almacen.infrastructure.entities.TipoMovimiento;

import java.util.List;
import java.util.Optional;

public interface RetrieveMovimientoUseCase {
    Optional<Movimiento> getMovimientoById(Long id);
    List<Movimiento> getAllMovimientos();
    List<Movimiento> findByTipoMovimiento(TipoMovimiento tipoMovimiento);
}

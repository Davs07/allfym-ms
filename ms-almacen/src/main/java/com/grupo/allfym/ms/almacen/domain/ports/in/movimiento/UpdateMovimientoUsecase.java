package com.grupo.allfym.ms.almacen.domain.ports.in.movimiento;

import com.grupo.allfym.ms.almacen.domain.models.Movimiento;

import java.util.Optional;

public interface UpdateMovimientoUsecase {
    Optional<Movimiento> updateMovimiento(Movimiento movimiento, Long idMovimiento);

}

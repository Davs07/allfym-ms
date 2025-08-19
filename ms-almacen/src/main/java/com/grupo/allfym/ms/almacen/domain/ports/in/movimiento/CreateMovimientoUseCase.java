package com.grupo.allfym.ms.almacen.domain.ports.in.movimiento;

import com.grupo.allfym.ms.almacen.domain.models.AlmacenProducto;
import com.grupo.allfym.ms.almacen.domain.models.Movimiento;
import com.grupo.allfym.ms.almacen.infrastructure.entities.TipoMovimiento;

public interface CreateMovimientoUseCase {
    Movimiento createMovimiento(AlmacenProducto almacenProducto, TipoMovimiento tipo, Integer cantidad);
}

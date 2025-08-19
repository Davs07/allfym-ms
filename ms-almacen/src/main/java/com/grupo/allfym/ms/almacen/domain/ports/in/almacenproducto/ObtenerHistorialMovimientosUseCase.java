package com.grupo.allfym.ms.almacen.domain.ports.in.almacenproducto;

import com.grupo.allfym.ms.almacen.domain.models.Movimiento;

import java.util.List;

public interface ObtenerHistorialMovimientosUseCase {
    List<Movimiento> obtenerHistorialMovimientos(Long idAlmacen);
}

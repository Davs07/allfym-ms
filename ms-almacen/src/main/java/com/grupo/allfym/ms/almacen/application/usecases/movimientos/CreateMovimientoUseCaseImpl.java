package com.grupo.allfym.ms.almacen.application.usecases.movimientos;

import com.grupo.allfym.ms.almacen.domain.models.AlmacenProducto;
import com.grupo.allfym.ms.almacen.domain.models.Movimiento;
import com.grupo.allfym.ms.almacen.domain.ports.in.movimiento.CreateMovimientoUseCase;
import com.grupo.allfym.ms.almacen.domain.ports.out.MovimientoRepositoryPort;
import com.grupo.allfym.ms.almacen.infrastructure.entities.FechaMovimiento;
import com.grupo.allfym.ms.almacen.infrastructure.entities.TipoMovimiento;

import java.time.LocalDate;

public class CreateMovimientoUseCaseImpl implements CreateMovimientoUseCase {

    private final MovimientoRepositoryPort movimientoRepositoryPort;

    public CreateMovimientoUseCaseImpl(MovimientoRepositoryPort movimientoRepositoryPort) {
        this.movimientoRepositoryPort = movimientoRepositoryPort;
    }

    @Override
    public Movimiento createMovimiento(AlmacenProducto almacenProducto, TipoMovimiento tipoMovimiento, Integer cantidad) {
        Movimiento movimiento = new Movimiento();
        movimiento.setTipoMovimiento(tipoMovimiento);
        movimiento.setCantidadDeMovimientos(cantidad);

        // Crear FechaMovimiento con la fecha actual
        LocalDate fecha = LocalDate.now();
        FechaMovimiento fechaMovimiento = new FechaMovimiento(fecha.getDayOfMonth(), fecha.getMonthValue(), fecha.getYear());
        movimiento.setFechaMovimiento(fechaMovimiento);

        // Asignar la referencia bidireccional
        movimiento.setAlmacenProducto(almacenProducto);

        // Agregar el movimiento a la lista del almacenProducto
        almacenProducto.getMovimientos().add(movimiento);

        return movimiento;
    }
}
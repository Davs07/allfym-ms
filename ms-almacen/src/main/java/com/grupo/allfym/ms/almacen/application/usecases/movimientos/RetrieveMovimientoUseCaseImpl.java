package com.grupo.allfym.ms.almacen.application.usecases.movimientos;

import com.grupo.allfym.ms.almacen.domain.models.Movimiento;
import com.grupo.allfym.ms.almacen.domain.ports.in.movimiento.RetrieveMovimientoUseCase;
import com.grupo.allfym.ms.almacen.domain.ports.out.MovimientoRepositoryPort;
import com.grupo.allfym.ms.almacen.infrastructure.entities.TipoMovimiento;

import java.util.List;
import java.util.Optional;

public class RetrieveMovimientoUseCaseImpl implements RetrieveMovimientoUseCase {

   private final MovimientoRepositoryPort movimientoRepositoryPort;

    public RetrieveMovimientoUseCaseImpl(MovimientoRepositoryPort movimientoRepositoryPort) {
        this.movimientoRepositoryPort = movimientoRepositoryPort;
    }

    @Override
    public Optional<Movimiento> getMovimientoById(Long id) {
        return movimientoRepositoryPort.findById(id);
    }

    @Override
    public List<Movimiento> getAllMovimientos() {
        return movimientoRepositoryPort.findAll();
    }

    @Override
    public List<Movimiento> findByTipoMovimiento(TipoMovimiento tipoMovimiento) {
        return movimientoRepositoryPort.findByTipoMovimiento(tipoMovimiento);
    }
}

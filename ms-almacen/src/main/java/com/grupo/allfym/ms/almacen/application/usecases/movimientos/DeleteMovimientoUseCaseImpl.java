package com.grupo.allfym.ms.almacen.application.usecases.movimientos;

import com.grupo.allfym.ms.almacen.domain.ports.in.movimiento.DeleteMovimientoUseCase;
import com.grupo.allfym.ms.almacen.domain.ports.out.MovimientoRepositoryPort;

public class DeleteMovimientoUseCaseImpl implements DeleteMovimientoUseCase {

    private final MovimientoRepositoryPort movimientoRepositoryPort;

    public DeleteMovimientoUseCaseImpl(MovimientoRepositoryPort movimientoRepositoryPort) {
        this.movimientoRepositoryPort = movimientoRepositoryPort;
    }

    @Override
    public boolean deleteMovimientoById(Long idMovimiento) {
        return movimientoRepositoryPort.deleteById(idMovimiento);
    }
}

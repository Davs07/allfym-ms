package com.grupo.allfym.ms.almacen.application.services;

import com.grupo.allfym.ms.almacen.domain.models.AlmacenProducto;
import com.grupo.allfym.ms.almacen.domain.models.Movimiento;
import com.grupo.allfym.ms.almacen.domain.ports.in.movimiento.CreateMovimientoUseCase;
import com.grupo.allfym.ms.almacen.domain.ports.in.movimiento.DeleteMovimientoUseCase;
import com.grupo.allfym.ms.almacen.domain.ports.in.movimiento.RetrieveMovimientoUseCase;
import com.grupo.allfym.ms.almacen.domain.ports.in.movimiento.UpdateMovimientoUsecase;
import com.grupo.allfym.ms.almacen.infrastructure.entities.TipoMovimiento;

import java.util.List;
import java.util.Optional;

public class MovimientoService implements CreateMovimientoUseCase, DeleteMovimientoUseCase, RetrieveMovimientoUseCase, UpdateMovimientoUsecase {

    private final CreateMovimientoUseCase createMovimientoUseCase;
    private final DeleteMovimientoUseCase deleteMovimientoUseCase;
    private final RetrieveMovimientoUseCase retrieveMovimientoUseCase;
    private final UpdateMovimientoUsecase updateMovimientoUsecase;

    public MovimientoService(CreateMovimientoUseCase createMovimientoUseCase, DeleteMovimientoUseCase deleteMovimientoUseCase, RetrieveMovimientoUseCase retrieveMovimientoUseCase, UpdateMovimientoUsecase updateMovimientoUsecase) {
        this.createMovimientoUseCase = createMovimientoUseCase;
        this.deleteMovimientoUseCase = deleteMovimientoUseCase;
        this.retrieveMovimientoUseCase = retrieveMovimientoUseCase;
        this.updateMovimientoUsecase = updateMovimientoUsecase;
    }

    @Override
    public Movimiento createMovimiento(AlmacenProducto almacenProducto, TipoMovimiento tipo, Integer cantidad) {
        return createMovimientoUseCase.createMovimiento(almacenProducto, tipo, cantidad);
    }

    @Override
    public boolean deleteMovimientoById(Long idMovimiento) {
        return deleteMovimientoUseCase.deleteMovimientoById(idMovimiento);
    }

    @Override
    public Optional<Movimiento> getMovimientoById(Long id) {
        return retrieveMovimientoUseCase.getMovimientoById(id);
    }

    @Override
    public List<Movimiento> getAllMovimientos() {
        return retrieveMovimientoUseCase.getAllMovimientos();
    }

    @Override
    public List<Movimiento> findByTipoMovimiento(TipoMovimiento tipoMovimiento) {
        return retrieveMovimientoUseCase.findByTipoMovimiento(tipoMovimiento);
    }

    @Override
    public Optional<Movimiento> updateMovimiento(Movimiento movimiento, Long idMovimiento) {
        return updateMovimientoUsecase.updateMovimiento(movimiento, idMovimiento);
    }


}

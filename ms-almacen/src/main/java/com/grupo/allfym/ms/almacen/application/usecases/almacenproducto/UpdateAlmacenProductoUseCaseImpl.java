package com.grupo.allfym.ms.almacen.application.usecases.almacenproducto;

import com.grupo.allfym.ms.almacen.domain.models.AlmacenProducto;
import com.grupo.allfym.ms.almacen.domain.ports.in.almacenproducto.UpdateAlmacenProductoUseCase;
import com.grupo.allfym.ms.almacen.domain.ports.out.AlmacenProductoRepositoryPort;

import java.util.Optional;

public class UpdateAlmacenProductoUseCaseImpl implements UpdateAlmacenProductoUseCase {

    private final AlmacenProductoRepositoryPort almacenProductoRepositoryPort;

    public UpdateAlmacenProductoUseCaseImpl(AlmacenProductoRepositoryPort almacenProductoRepositoryPort) {
        this.almacenProductoRepositoryPort = almacenProductoRepositoryPort;
    }

    @Override
    public Optional<AlmacenProducto> updateProducto(AlmacenProducto almacenProductoActualizado, Long idProducto) {
        return almacenProductoRepositoryPort.update(almacenProductoActualizado);
    }
}

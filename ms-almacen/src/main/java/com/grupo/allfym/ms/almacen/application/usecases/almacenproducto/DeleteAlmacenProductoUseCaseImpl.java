package com.grupo.allfym.ms.almacen.application.usecases.almacenproducto;

import com.grupo.allfym.ms.almacen.domain.ports.in.almacenproducto.DeleteAlmacenProductoUseCase;
import com.grupo.allfym.ms.almacen.domain.ports.out.AlmacenProductoRepositoryPort;

public class DeleteAlmacenProductoUseCaseImpl implements DeleteAlmacenProductoUseCase {

   private final AlmacenProductoRepositoryPort almacenProductoRepositoryPort;

    public DeleteAlmacenProductoUseCaseImpl(AlmacenProductoRepositoryPort almacenProductoRepositoryPort) {
        this.almacenProductoRepositoryPort = almacenProductoRepositoryPort;
    }

    @Override
    public boolean deleteAlmacenById(Long idAlmacen) {
        return almacenProductoRepositoryPort.deleteByIdAlmacen(idAlmacen);
    }
}

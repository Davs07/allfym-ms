package com.grupo.allfym.ms.almacen.application.usecases.almacenproducto;

import com.grupo.allfym.ms.almacen.domain.models.AlmacenProducto;
import com.grupo.allfym.ms.almacen.domain.ports.in.almacenproducto.RetrieveAlmacenProductoUseCase;
import com.grupo.allfym.ms.almacen.domain.ports.out.AlmacenProductoRepositoryPort;

import java.util.List;
import java.util.Optional;

public class RetrieveAlmacenProductoUseCaseImpl implements RetrieveAlmacenProductoUseCase {

    private final AlmacenProductoRepositoryPort almacenProductoRepositoryPort;

    public RetrieveAlmacenProductoUseCaseImpl(AlmacenProductoRepositoryPort almacenProductoRepositoryPort) {
        this.almacenProductoRepositoryPort = almacenProductoRepositoryPort;
    }


    @Override
    public Optional<AlmacenProducto> getAlmacenProductById(Long id) {
        return almacenProductoRepositoryPort.findByIdAlmacen(id);
    }

    @Override
    public List<AlmacenProducto> getAllAlmacenProducts() {
        return almacenProductoRepositoryPort.findAll();
    }
}

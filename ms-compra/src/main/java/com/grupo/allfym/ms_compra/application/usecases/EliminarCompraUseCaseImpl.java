package com.grupo.allfym.ms_compra.application.usecases;

import com.grupo.allfym.ms.compra.domain.ports.in.EliminarCompraUseCase;
import com.grupo.allfym.ms.compra.domain.ports.out.CompraRepositoryPort;

public class EliminarCompraUseCaseImpl implements EliminarCompraUseCase {
    private final CompraRepositoryPort compraRepositoryPort;

    public EliminarCompraUseCaseImpl(CompraRepositoryPort compraRepositoryPort) {
        this.compraRepositoryPort = compraRepositoryPort;
    }

    @Override
    public void eliminar(Long id) {
        compraRepositoryPort.eliminar(id);
    }
}

package com.grupo.allfym.ms_compra.application.usecases;

import com.grupo.allfym.ms.compra.domain.models.entities.Compra;
import com.grupo.allfym.ms.compra.domain.ports.in.ListarComprasUseCase;
import com.grupo.allfym.ms.compra.domain.ports.out.CompraRepositoryPort;

import java.util.List;

public class ListarComprasUseCaseImpl implements ListarComprasUseCase {
    private final CompraRepositoryPort compraRepositoryPort;

    public ListarComprasUseCaseImpl(CompraRepositoryPort compraRepositoryPort) {
        this.compraRepositoryPort = compraRepositoryPort;
    }

    @Override
    public List<Compra> listar() {
        return compraRepositoryPort.listar();
    }
}

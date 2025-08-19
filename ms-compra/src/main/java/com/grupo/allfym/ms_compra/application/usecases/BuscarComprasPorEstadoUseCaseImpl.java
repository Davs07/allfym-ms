package com.grupo.allfym.ms_compra.application.usecases;

import com.grupo.allfym.ms.compra.domain.models.entities.Compra;
import com.grupo.allfym.ms.compra.domain.models.enums.Estado;
import com.grupo.allfym.ms.compra.domain.ports.in.BuscarComprasPorEstadoUseCase;
import com.grupo.allfym.ms.compra.domain.ports.out.CompraRepositoryPort;

import java.util.List;

public class BuscarComprasPorEstadoUseCaseImpl implements BuscarComprasPorEstadoUseCase {
    private final CompraRepositoryPort compraRepositoryPort;

    public BuscarComprasPorEstadoUseCaseImpl(CompraRepositoryPort compraRepositoryPort) {
        this.compraRepositoryPort = compraRepositoryPort;
    }

    @Override
    public List<Compra> buscarPorEstado(Estado estado) {
        return compraRepositoryPort.buscarPorEstado(estado);
    }
}

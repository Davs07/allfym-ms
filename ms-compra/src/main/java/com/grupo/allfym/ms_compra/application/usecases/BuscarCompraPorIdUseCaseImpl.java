package com.grupo.allfym.ms_compra.application.usecases;

import com.grupo.allfym.ms_compra.domain.models.entities.Compra;
import com.grupo.allfym.ms_compra.domain.ports.in.BuscarCompraPorIdUseCase;
import com.grupo.allfym.ms_compra.domain.ports.out.CompraRepositoryPort;

import java.util.Optional;

public class BuscarCompraPorIdUseCaseImpl implements BuscarCompraPorIdUseCase {
    private final CompraRepositoryPort compraRepositoryPort;

    public BuscarCompraPorIdUseCaseImpl(CompraRepositoryPort compraRepositoryPort) {
        this.compraRepositoryPort = compraRepositoryPort;
    }

    @Override
    public Optional<Compra> buscarPorId(Long id) {
        return compraRepositoryPort.buscarPorId(id);
    }
}
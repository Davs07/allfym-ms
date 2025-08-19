package com.grupo.allfym.ms_compra.application.usecases;

import com.grupo.allfym.ms_compra.domain.models.entities.Compra;
import com.grupo.allfym.ms_compra.domain.models.enums.Estado;
import com.grupo.allfym.ms_compra.domain.ports.in.CambiarEstadoCompraUseCase;
import com.grupo.allfym.ms_compra.domain.ports.out.CompraRepositoryPort;

import java.util.Optional;

public class CambiarEstadoCompraUseCaseImpl implements CambiarEstadoCompraUseCase {
    private final CompraRepositoryPort compraRepositoryPort;

    public CambiarEstadoCompraUseCaseImpl(CompraRepositoryPort compraRepositoryPort) {
        this.compraRepositoryPort = compraRepositoryPort;
    }

    @Override
    public void cambiarEstado(Long id, String estadoNuevo) {
        Optional<Compra> compraOpt = compraRepositoryPort.buscarPorId(id);
        if (compraOpt.isEmpty()) {
            throw new IllegalArgumentException("No se encontró la compra");
        }

        try {
            Estado estado = Estado.valueOf(estadoNuevo.toUpperCase());
            Compra compraActualizada = compraOpt.get().cambiarEstado(estado);
            compraRepositoryPort.guardar(compraActualizada);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Estado inválido: " + estadoNuevo);
        }
    }
}

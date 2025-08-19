package com.grupo.allfym.ms_compra.domain.ports.in;

import com.grupo.allfym.ms_compra.domain.models.entities.Compra;

import java.util.Optional;

public interface BuscarCompraPorIdUseCase {
    Optional<Compra> buscarPorId(Long id);
}

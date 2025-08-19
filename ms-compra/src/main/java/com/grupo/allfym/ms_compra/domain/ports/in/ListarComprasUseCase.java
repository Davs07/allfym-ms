package com.grupo.allfym.ms_compra.domain.ports.in;

import com.grupo.allfym.ms_compra.domain.models.entities.Compra;

import java.util.List;

public interface ListarComprasUseCase {
    List<Compra> listar();
}

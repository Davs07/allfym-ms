package com.grupo.allfym.ms_compra.domain.ports.in;

import com.grupo.allfym.ms_compra.domain.models.entities.Compra;
import com.grupo.allfym.ms_compra.domain.models.enums.Estado;

import java.util.List;

public interface BuscarComprasPorEstadoUseCase {
    List<Compra> buscarPorEstado(Estado estado);
}

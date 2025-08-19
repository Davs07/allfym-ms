package com.grupo.allfym.ms_compra.domain.ports.in;

import com.grupo.allfym.ms.compra.domain.models.entities.Compra;

public interface GuardarCompraUseCase {
    Compra guardar(Compra compra);
}

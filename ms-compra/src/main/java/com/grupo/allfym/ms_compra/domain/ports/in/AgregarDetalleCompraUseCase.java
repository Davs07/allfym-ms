package com.grupo.allfym.ms_compra.domain.ports.in;

import com.grupo.allfym.ms_compra.domain.models.entities.Compra;
import com.grupo.allfym.ms_compra.domain.models.entities.DetalleCompra;

public interface AgregarDetalleCompraUseCase {
    Compra agregarDetalle(Long idCompra, DetalleCompra detalleCompra);
}

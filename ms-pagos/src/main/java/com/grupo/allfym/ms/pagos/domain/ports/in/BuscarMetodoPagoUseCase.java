package com.grupo.allfym.ms.pagos.domain.ports.in;

import com.grupo.allfym.ms.pagos.domain.models.classes.Pago;

import java.util.List;

public interface BuscarMetodoPagoUseCase {
    List<Pago> buscarMetodoPago(String metodo);
}

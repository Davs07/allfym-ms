package com.grupo.allfym.ms.pagos.domain.ports.in;

import com.grupo.allfym.ms.pagos.domain.models.classes.Pago;

import java.util.List;
import java.util.Optional;

public interface ObtenerPagoUseCase {
    Optional<Pago> obtenerPago(Long id);
    List<Pago> listaPago();
}

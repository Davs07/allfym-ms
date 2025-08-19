package com.grupo.allfym.ms.pagos.domain.ports.in;

import com.grupo.allfym.ms.pagos.domain.models.classes.Pago;

import java.util.Optional;

public interface ActualizarPagoUseCase {
    Optional<Pago> actualizarPago(Long id, Pago pago);
}

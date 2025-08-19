package com.grupo.allfym.ms.pagos.domain.ports.in;

import com.grupo.allfym.ms.pagos.domain.models.classes.Pago;

public interface GuardarPagoUseCase {
    Pago guardar(Pago pago);
}

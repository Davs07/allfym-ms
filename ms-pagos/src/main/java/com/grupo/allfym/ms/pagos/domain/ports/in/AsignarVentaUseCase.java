package com.grupo.allfym.ms.pagos.domain.ports.in;

import com.grupo.allfym.ms.pagos.domain.models.Venta;

import java.util.Optional;

public interface AsignarVentaUseCase {
    Optional<Venta> asignarVenta(Venta venta, Long id);
}

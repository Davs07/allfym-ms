package com.grupo.allfym.ms.pagos.domain.ports.in;

import com.grupo.allfym.ms.pagos.domain.models.Venta;

import java.util.Optional;

public interface RemoverVentaUseCase {
    Optional<Venta> removerVenta(Venta venta, Long id);
}

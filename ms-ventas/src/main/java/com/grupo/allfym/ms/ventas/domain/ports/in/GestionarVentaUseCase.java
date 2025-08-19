package com.grupo.allfym.ms.ventas.domain.ports.in;

import com.grupo.allfym.ms.ventas.domain.models.entities.Venta;

public interface GestionarVentaUseCase {

    Venta confirmarVenta(Long ventaId);

    Venta cancelarVenta(Long ventaId);

    Venta marcarComoEntregada(Long ventaId);

    void eliminarVenta(Long ventaId);
}

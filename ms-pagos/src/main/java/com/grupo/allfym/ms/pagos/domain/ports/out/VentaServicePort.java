package com.grupo.allfym.ms.pagos.domain.ports.out;

import com.grupo.allfym.ms.pagos.domain.models.Venta;

import java.util.List;

public interface VentaServicePort {
    List<Venta> listaVenta();
    Venta detalleVenta(Long id);
}

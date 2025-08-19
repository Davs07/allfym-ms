package com.grupo.allfym.ms.ventas.domain.ports.in;

import com.grupo.allfym.ms.ventas.domain.models.entities.Venta;

/**
 * Puerto de entrada para crear ventas (recibe el agregado de dominio).
 */
public interface CrearVentaUseCase {

    Venta crear(Venta venta);
}

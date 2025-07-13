package com.grupo.allfym.ms.ventas.services;

import com.grupo.allfym.ms.ventas.entity.DetalleVenta;
import com.grupo.allfym.ms.ventas.models.VentaRequestDTO.DetalleVentaDTO;

import java.util.List;
import java.util.Optional;

public interface DetalleVentaService {

    // Método de dominio: agregar detalle
    DetalleVenta agregarDetalle(Long ventaId, DetalleVentaDTO detalleDTO);

    Optional<DetalleVenta> buscarPorId(Long id);

    List<DetalleVenta> buscarPorVentaId(Long ventaId);

    List<DetalleVenta> buscarPorProducto(String producto);

    DetalleVenta actualizarDetalle(Long id, DetalleVentaDTO detalleDTO);

    void eliminarDetalle(Long id);
}

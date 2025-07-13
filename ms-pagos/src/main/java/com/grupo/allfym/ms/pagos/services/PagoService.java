package com.grupo.allfym.ms.pagos.services;

import com.grupo.allfym.ms.pagos.entity.Pago;
import com.grupo.allfym.ms.pagos.models.PagoRequestDTO;
import com.grupo.allfym.ms.pagos.models.PagoResponseDTO;

import java.util.List;
import java.util.Optional;

public interface PagoService {
    // Métodos existentes
    List<Pago> lista();
    Optional<Pago> porId(Long id);
    Pago guardar(Pago pago);
    void eliminar(Long id);

    // Nuevos métodos con DTOs y comunicación con ms-ventas
    PagoResponseDTO crearPago(PagoRequestDTO pagoRequest);
    Optional<PagoResponseDTO> obtenerPagoPorId(Long id);
    List<PagoResponseDTO> obtenerTodosLosPagos();
    List<PagoResponseDTO> obtenerPagosPorVenta(Long ventaId);
    PagoResponseDTO actualizarEstadoPago(Long id, String nuevoEstado);
}

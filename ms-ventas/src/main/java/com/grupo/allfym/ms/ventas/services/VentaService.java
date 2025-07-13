package com.grupo.allfym.ms.ventas.services;

import com.grupo.allfym.ms.ventas.entity.Venta;
import com.grupo.allfym.ms.ventas.enums.EstadoVenta;
import com.grupo.allfym.ms.ventas.models.VentaRequestDTO;
import com.grupo.allfym.ms.ventas.models.VentaResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VentaService {

    // Método de dominio: agregar venta
    VentaResponseDTO agregarVenta(VentaRequestDTO ventaRequest);

    // Método de dominio: buscar por ID
    Optional<VentaResponseDTO> buscarPorId(Long id);

    List<VentaResponseDTO> obtenerTodasLasVentas();

    List<VentaResponseDTO> buscarPorClienteId(Long clienteId);

    List<VentaResponseDTO> buscarPorEstado(EstadoVenta estado);

    List<VentaResponseDTO> buscarPorFechaRegistro(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    VentaResponseDTO confirmarVenta(Long ventaId);

    VentaResponseDTO cancelarVenta(Long ventaId);

    void eliminarVenta(Long id);
}

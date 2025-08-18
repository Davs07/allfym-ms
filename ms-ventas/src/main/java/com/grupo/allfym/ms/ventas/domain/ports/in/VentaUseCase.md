package com.grupo.allfym.ms.ventas.domain.ports.in;

import com.grupo.allfym.ms.ventas.domain.models.entities.Venta;
import com.grupo.allfym.ms.ventas.domain.models.enums.EstadoVenta;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Puerto de entrada para todas las operaciones de ventas.
 * Mantiene la simplicidad del VentaService original pero usando entidades de dominio.
 */
public interface VentaUseCase {

    /**
     * Crea una nueva venta.
     */
    Venta crearVenta(Venta venta);

    /**
     * Busca una venta por ID.
     */
    Optional<Venta> buscarPorId(Long id);

    /**
     * Obtiene todas las ventas.
     */
    List<Venta> obtenerTodasLasVentas();

    /**
     * Busca ventas por cliente.
     */
    List<Venta> buscarPorClienteId(Long clienteId);

    /**
     * Busca ventas por estado.
     */
    List<Venta> buscarPorEstado(EstadoVenta estado);

    /**
     * Busca ventas por rango de fechas.
     */
    List<Venta> buscarPorFechaRegistro(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    /**
     * Confirma una venta.
     */
    Venta confirmarVenta(Long ventaId);

    /**
     * Cancela una venta.
     */
    Venta cancelarVenta(Long ventaId);

    /**
     * Elimina una venta.
     */
    void eliminarVenta(Long id);
}

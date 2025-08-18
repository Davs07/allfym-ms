package com.grupo.allfym.ms.ventas.application.usecases;

import com.grupo.allfym.ms.ventas.domain.models.entities.Venta;
import com.grupo.allfym.ms.ventas.domain.models.entities.DetalleVenta;
import com.grupo.allfym.ms.ventas.domain.models.enums.EstadoVenta;
import com.grupo.allfym.ms.ventas.domain.ports.in.GestionarVentaUseCase;
import com.grupo.allfym.ms.ventas.domain.ports.out.VentaRepositoryPort;
import com.grupo.allfym.ms.ventas.domain.ports.out.AlmacenServicePort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación para gestionar ventas.
 */
@Component
@Transactional
public class GestionarVentaUseCaseImpl implements GestionarVentaUseCase {

    private final VentaRepositoryPort ventaRepository;
    private final AlmacenServicePort almacenService;

    public GestionarVentaUseCaseImpl(VentaRepositoryPort ventaRepository,
                                    AlmacenServicePort almacenService) {
        this.ventaRepository = ventaRepository;
        this.almacenService = almacenService;
    }

    @Override
    public Venta confirmarVenta(Long ventaId) {
        Venta venta = ventaRepository.buscarPorId(ventaId)
                .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada: " + ventaId));

        if (venta.getEstado() != EstadoVenta.PENDIENTE) {
            throw new IllegalStateException("Solo se pueden confirmar ventas pendientes. Estado actual: " + venta.getEstado());
        }

        venta.confirmarVenta();
        return ventaRepository.guardar(venta);
    }

    @Override
    public Venta cancelarVenta(Long ventaId) {
        Venta venta = ventaRepository.buscarPorId(ventaId)
                .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada: " + ventaId));

        if (venta.getEstado() == EstadoVenta.CANCELADA || venta.getEstado() == EstadoVenta.ENTREGADA) {
            throw new IllegalStateException("No se puede cancelar una venta en estado: " + venta.getEstado());
        }

        venta.cancelarVenta();
        
        // Restaurar stock si la venta tenía productos
        for (DetalleVenta detalle : venta.getDetalles()) {
            try {
                almacenService.restaurarStock(detalle.getProductoId(), detalle.getCantidad());
            } catch (Exception e) {
                // Log del error, pero no falla la cancelación
                System.err.println("Error al restaurar stock: " + e.getMessage());
            }
        }

        return ventaRepository.guardar(venta);
    }

    @Override
    public Venta marcarComoEntregada(Long ventaId) {
        Venta venta = ventaRepository.buscarPorId(ventaId)
                .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada: " + ventaId));

        if (venta.getEstado() != EstadoVenta.CONFIRMADA) {
            throw new IllegalStateException("Solo se pueden entregar ventas confirmadas. Estado actual: " + venta.getEstado());
        }

        venta.marcarComoEntregada();
        return ventaRepository.guardar(venta);
    }

    @Override
    public void eliminarVenta(Long ventaId) {
        Venta venta = ventaRepository.buscarPorId(ventaId)
                .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada: " + ventaId));

        // Solo permitir eliminar ventas pendientes o canceladas
        if (venta.getEstado() == EstadoVenta.CONFIRMADA || venta.getEstado() == EstadoVenta.ENTREGADA) {
            throw new IllegalStateException("No se pueden eliminar ventas confirmadas o entregadas. Estado actual: " + venta.getEstado());
        }

        ventaRepository.eliminarPorId(ventaId);
    }
}

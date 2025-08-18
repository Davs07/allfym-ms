package com.grupo.allfym.ms.ventas.domain.ports.in;

import com.grupo.allfym.ms.ventas.domain.models.entities.Venta;

/**
 * Puerto de entrada para gestionar el estado de las ventas.
 * Define el contrato para los casos de uso de cambio de estado.
 */
public interface GestionarVentaUseCase {
    
    /**
     * Confirma una venta que está en estado pendiente.
     * 
     * @param ventaId ID de la venta a confirmar
     * @return La venta confirmada
     * @throws IllegalArgumentException si la venta no existe
     * @throws IllegalStateException si la venta no puede ser confirmada
     */
    Venta confirmarVenta(Long ventaId);
    
    /**
     * Cancela una venta que no está en estado final.
     * 
     * @param ventaId ID de la venta a cancelar
     * @return La venta cancelada
     * @throws IllegalArgumentException si la venta no existe
     * @throws IllegalStateException si la venta no puede ser cancelada
     */
    Venta cancelarVenta(Long ventaId);
    
    /**
     * Marca una venta como entregada.
     * 
     * @param ventaId ID de la venta a marcar como entregada
     * @return La venta marcada como entregada
     * @throws IllegalArgumentException si la venta no existe
     * @throws IllegalStateException si la venta no puede ser marcada como entregada
     */
    Venta marcarComoEntregada(Long ventaId);
    
    /**
     * Elimina una venta del sistema.
     * Solo se pueden eliminar ventas en estado pendiente o cancelada.
     * 
     * @param ventaId ID de la venta a eliminar
     * @throws IllegalArgumentException si la venta no existe
     * @throws IllegalStateException si la venta no puede ser eliminada
     */
    void eliminarVenta(Long ventaId);
}

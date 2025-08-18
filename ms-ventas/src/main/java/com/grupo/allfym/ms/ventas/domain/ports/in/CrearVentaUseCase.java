package com.grupo.allfym.ms.ventas.domain.ports.in;

import com.grupo.allfym.ms.ventas.domain.models.entities.Venta;

/**
 * Puerto de entrada para crear ventas.
 * Define el contrato para el caso de uso de creación de ventas.
 */
public interface CrearVentaUseCase {
    
    /**
     * Crea una nueva venta con los detalles especificados.
     * 
     * @param command Comando con la información necesaria para crear la venta
     * @return La venta creada
     * @throws IllegalArgumentException si los datos del comando son inválidos
     * @throws IllegalStateException si no se puede crear la venta por reglas de negocio
     */
    Venta crear(CrearVentaCommand command);
    
    /**
     * Comando para crear una venta.
     */
    record CrearVentaCommand(
        Long clienteId,
        String metodoPago,
        java.util.List<DetalleVentaCommand> detalles
    ) {
        public CrearVentaCommand {
            if (clienteId == null) {
                throw new IllegalArgumentException("El ID del cliente es requerido");
            }
            if (metodoPago == null || metodoPago.trim().isEmpty()) {
                throw new IllegalArgumentException("El método de pago es requerido");
            }
            if (detalles == null || detalles.isEmpty()) {
                throw new IllegalArgumentException("La venta debe tener al menos un detalle");
            }
        }
    }
    
    /**
     * Comando para crear un detalle de venta.
     */
    record DetalleVentaCommand(
        Long productoId,
        int cantidad,
        java.math.BigDecimal precioUnitario
    ) {
        public DetalleVentaCommand {
            if (productoId == null) {
                throw new IllegalArgumentException("El ID del producto es requerido");
            }
            if (cantidad <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
            }
            if (precioUnitario == null || precioUnitario.compareTo(java.math.BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("El precio unitario debe ser mayor a cero");
            }
        }
    }
}

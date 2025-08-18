package com.grupo.allfym.ms.ventas.domain.ports.out;

/**
 * Puerto de salida para el servicio de almacén.
 * Define el contrato para gestionar el stock de productos.
 */
public interface AlmacenServicePort {
    
    /**
     * Reduce el stock de un producto en la cantidad especificada.
     * 
     * @param productoId ID del producto
     * @param cantidad Cantidad a reducir del stock
     * @throws RuntimeException si no hay suficiente stock o hay error en la comunicación
     */
    void reducirStock(Long productoId, int cantidad);
    
    /**
     * Verifica si hay suficiente stock para un producto.
     * 
     * @param productoId ID del producto
     * @param cantidad Cantidad requerida
     * @return true si hay suficiente stock, false en caso contrario
     */
    boolean hayStockSuficiente(Long productoId, int cantidad);
    
    /**
     * Restaura el stock de un producto (usado en caso de cancelación de venta).
     * 
     * @param productoId ID del producto
     * @param cantidad Cantidad a restaurar al stock
     * @throws RuntimeException si hay error en la comunicación con el servicio externo
     */
    void restaurarStock(Long productoId, int cantidad);
}

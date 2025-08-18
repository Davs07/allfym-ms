package com.grupo.allfym.ms.ventas.domain.ports.out;

import com.grupo.allfym.ms.ventas.domain.models.Producto;

import java.util.Optional;

/**
 * Puerto de salida para el servicio de productos.
 * Define el contrato para obtener información de productos desde servicios externos.
 */
public interface ProductoServicePort {
    
    /**
     * Busca un producto por su ID.
     * 
     * @param productoId ID del producto
     * @return El producto encontrado o empty si no existe
     * @throws RuntimeException si hay error en la comunicación con el servicio externo
     */
    Optional<Producto> buscarPorId(Long productoId);
    
    /**
     * Verifica si un producto existe y tiene información completa.
     * 
     * @param productoId ID del producto
     * @return true si el producto existe y tiene información válida, false en caso contrario
     */
    boolean existeYEsValido(Long productoId);
}

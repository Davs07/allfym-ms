package com.grupo.allfym.ms.ventas.domain.ports.out;

import com.grupo.allfym.ms.ventas.domain.models.Cliente;

import java.util.Optional;

/**
 * Puerto de salida para el servicio de clientes.
 * Define el contrato para obtener información de clientes desde servicios externos.
 */
public interface ClienteServicePort {
    
    /**
     * Busca un cliente por su ID.
     * 
     * @param clienteId ID del cliente
     * @return El cliente encontrado o empty si no existe
     * @throws RuntimeException si hay error en la comunicación con el servicio externo
     */
    Optional<Cliente> buscarPorId(Long clienteId);
    
    /**
     * Verifica si un cliente existe y está activo.
     * 
     * @param clienteId ID del cliente
     * @return true si el cliente existe y está activo, false en caso contrario
     */
    boolean existeYEstaActivo(Long clienteId);
}

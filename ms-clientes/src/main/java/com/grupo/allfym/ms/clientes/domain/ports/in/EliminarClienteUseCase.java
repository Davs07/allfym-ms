package com.grupo.allfym.ms.clientes.domain.ports.in;

/**
 * Puerto de entrada para eliminar clientes
 */
public interface EliminarClienteUseCase {

    /**
     * Elimina un cliente del sistema
     * @param id ID del cliente a eliminar
     * @throws RuntimeException si el cliente no existe
     */
    void eliminar(Long id);

    /**
     * Verifica si un cliente existe antes de eliminarlo
     * @param id ID del cliente
     * @return true si el cliente existe y se puede eliminar
     */
    boolean puedeEliminar(Long id);
}

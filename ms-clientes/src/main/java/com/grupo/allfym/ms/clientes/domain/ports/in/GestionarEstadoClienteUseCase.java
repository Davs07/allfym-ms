package com.grupo.allfym.ms.clientes.domain.ports.in;

import com.grupo.allfym.ms.clientes.domain.models.entities.Cliente;

/**
 * Puerto de entrada para gestionar el estado de clientes
 */
public interface GestionarEstadoClienteUseCase {

    /**
     * Activa un cliente
     * @param id ID del cliente
     * @return Cliente activado
     * @throws RuntimeException si el cliente no existe o no se puede activar
     */
    Cliente activar(Long id);

    /**
     * Desactiva un cliente
     * @param id ID del cliente
     * @return Cliente desactivado
     * @throws RuntimeException si el cliente no existe o no se puede desactivar
     */
    Cliente desactivar(Long id);

    /**
     * Suspende un cliente
     * @param id ID del cliente
     * @return Cliente suspendido
     * @throws RuntimeException si el cliente no existe o no se puede suspender
     */
    Cliente suspender(Long id);
}

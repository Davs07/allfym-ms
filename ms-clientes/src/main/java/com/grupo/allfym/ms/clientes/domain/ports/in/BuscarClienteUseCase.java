package com.grupo.allfym.ms.clientes.domain.ports.in;

import com.grupo.allfym.ms.clientes.domain.models.entities.Cliente;
import com.grupo.allfym.ms.clientes.domain.models.ov.EmailAddress;

import java.util.Optional;

/**
 * Puerto de entrada para buscar clientes
 */
public interface BuscarClienteUseCase {

    /**
     * Busca un cliente por su ID
     * @param id ID del cliente
     * @return Optional con el cliente si existe
     */
    Optional<Cliente> buscarPorId(Long id);

    /**
     * Busca un cliente por su email
     * @param email Email del cliente
     * @return Optional con el cliente si existe
     */
    Optional<Cliente> buscarPorEmail(EmailAddress email);

    /**
     * Busca un cliente por su DNI
     * @param dni DNI del cliente
     * @return Optional con el cliente si existe
     */
    Optional<Cliente> buscarPorDni(String dni);

    /**
     * Obtiene un cliente por ID, lanzando excepción si no existe
     * @param id ID del cliente
     * @return Cliente encontrado
     * @throws RuntimeException si el cliente no existe
     */
    Cliente obtenerPorId(Long id);
}

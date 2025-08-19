package com.grupo.allfym.ms.clientes.domain.ports.in;

import com.grupo.allfym.ms.clientes.domain.models.entities.Cliente;
import com.grupo.allfym.ms.clientes.domain.models.ov.EmailAddress;
import com.grupo.allfym.ms.clientes.domain.models.ov.Telefono;

/**
 * Puerto de entrada para actualizar datos de clientes
 */
public interface ActualizarClienteUseCase {

    /**
     * Actualiza los datos básicos de un cliente
     * @param id ID del cliente
     * @param nombre Nuevo nombre
     * @param apellido Nuevo apellido
     * @param direccion Nueva dirección
     * @return Cliente actualizado
     * @throws RuntimeException si el cliente no existe
     */
    Cliente actualizarDatos(Long id, String nombre, String apellido, String direccion);

    /**
     * Cambia el email de un cliente
     * @param id ID del cliente
     * @param nuevoEmail Nuevo email
     * @return Cliente actualizado
     * @throws RuntimeException si el cliente no existe o el email ya está en uso
     */
    Cliente cambiarEmail(Long id, EmailAddress nuevoEmail);

    /**
     * Cambia el teléfono de un cliente
     * @param id ID del cliente
     * @param nuevoTelefono Nuevo teléfono
     * @return Cliente actualizado
     * @throws RuntimeException si el cliente no existe
     */
    Cliente cambiarTelefono(Long id, Telefono nuevoTelefono);
}

package com.grupo.allfym.ms.clientes.domain.ports.in;

import com.grupo.allfym.ms.clientes.domain.models.entities.Cliente;
import com.grupo.allfym.ms.clientes.domain.models.ov.EmailAddress;
import com.grupo.allfym.ms.clientes.domain.models.ov.Telefono;

/**
 * Puerto de entrada para crear un nuevo cliente
 */
public interface CrearClienteUseCase {

    /**
     * Crea un nuevo cliente en el sistema
     * @param nombre Nombre del cliente
     * @param apellido Apellido del cliente
     * @param dni DNI del cliente
     * @param email Email del cliente
     * @param telefono Teléfono del cliente
     * @param direccion Dirección del cliente (opcional)
     * @return Cliente creado
     * @throws IllegalArgumentException si los datos son inválidos
     * @throws RuntimeException si el email o DNI ya existen
     */
    Cliente crear(String nombre, String apellido, String dni, EmailAddress email, 
                  Telefono telefono, String direccion);
}

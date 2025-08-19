package com.grupo.allfym.ms.clientes.domain.ports.in;

import com.grupo.allfym.ms.clientes.domain.models.entities.Cliente;
import com.grupo.allfym.ms.clientes.domain.models.ov.EmailAddress;
import com.grupo.allfym.ms.clientes.domain.models.ov.Telefono;

public interface ActualizarClienteUseCase {

    Cliente actualizarDatos(Long id, String nombre, String apellido, String direccion);

    Cliente cambiarEmail(Long id, EmailAddress nuevoEmail);

    Cliente cambiarTelefono(Long id, Telefono nuevoTelefono);
}

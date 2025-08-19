package com.grupo.allfym.ms.clientes.domain.ports.in;

import com.grupo.allfym.ms.clientes.domain.models.entities.Cliente;
import com.grupo.allfym.ms.clientes.domain.models.ov.EmailAddress;
import com.grupo.allfym.ms.clientes.domain.models.ov.Telefono;

public interface CrearClienteUseCase {

    Cliente crear(String nombre, String apellido, String dni, EmailAddress email, 
                  Telefono telefono, String direccion);
}

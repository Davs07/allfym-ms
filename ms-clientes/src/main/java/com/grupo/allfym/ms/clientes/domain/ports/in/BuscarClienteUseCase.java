package com.grupo.allfym.ms.clientes.domain.ports.in;

import com.grupo.allfym.ms.clientes.domain.models.entities.Cliente;
import com.grupo.allfym.ms.clientes.domain.models.ov.EmailAddress;

import java.util.Optional;

public interface BuscarClienteUseCase {

    Optional<Cliente> buscarPorId(Long id);

    Optional<Cliente> buscarPorEmail(EmailAddress email);

    Optional<Cliente> buscarPorDni(String dni);

    Cliente obtenerPorId(Long id);
}

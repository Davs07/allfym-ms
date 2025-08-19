package com.grupo.allfym.ms.reclamos.domain.ports.in;

import com.grupo.allfym.ms.reclamos.domain.models.Cliente;

import java.util.Optional;

public interface AsignarClienteUseCase {
    Optional<Cliente> asignarCliente(Cliente cliente, Long id);
}

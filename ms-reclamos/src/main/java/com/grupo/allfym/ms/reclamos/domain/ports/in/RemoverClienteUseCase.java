package com.grupo.allfym.ms.reclamos.domain.ports.in;

import com.grupo.allfym.ms.reclamos.domain.models.Cliente;

import java.util.Optional;

public interface RemoverClienteUseCase {
    Optional<Cliente> removerCliente(Cliente cliente, Long id);
}

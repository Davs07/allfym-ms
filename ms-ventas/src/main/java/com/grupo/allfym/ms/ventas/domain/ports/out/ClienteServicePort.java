package com.grupo.allfym.ms.ventas.domain.ports.out;

import com.grupo.allfym.ms.ventas.domain.models.Cliente;

import java.util.Optional;

public interface ClienteServicePort {
    
    Optional<Cliente> buscarPorId(Long clienteId);
    
    boolean existeYEstaActivo(Long clienteId);
}

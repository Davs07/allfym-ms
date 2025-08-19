package com.grupo.allfym.ms.reclamos.domain.ports.out;

import com.grupo.allfym.ms.reclamos.domain.models.Cliente;

import java.util.List;

public interface ClienteServicePort {
    List<Cliente> lista_clientes();
    Cliente detalleCliente(Long id);
}

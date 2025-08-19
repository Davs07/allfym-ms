package com.grupo.allfym.ms.clientes.domain.ports.in;

import com.grupo.allfym.ms.clientes.domain.models.entities.Cliente;

public interface GestionarEstadoClienteUseCase {

    Cliente activar(Long id);

    Cliente desactivar(Long id);

    Cliente suspender(Long id);
}

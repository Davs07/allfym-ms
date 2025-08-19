package com.grupo.allfym.ms.clientes.domain.ports.in;

public interface EliminarClienteUseCase {

    void eliminar(Long id);

    boolean puedeEliminar(Long id);
}

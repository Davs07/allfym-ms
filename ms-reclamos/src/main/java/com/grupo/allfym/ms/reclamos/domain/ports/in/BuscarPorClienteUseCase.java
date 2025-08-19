package com.grupo.allfym.ms.reclamos.domain.ports.in;

import com.grupo.allfym.ms.reclamos.domain.models.classes.Reclamo;

import java.util.List;

public interface BuscarPorClienteUseCase {
    List<Reclamo> buscarCliente(String nombre);
}

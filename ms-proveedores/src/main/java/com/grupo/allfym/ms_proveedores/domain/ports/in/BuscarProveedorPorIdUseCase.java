package com.grupo.allfym.ms_proveedores.domain.ports.in;

import com.grupo.allfym.ms_proveedores.domain.models.entities.Proveedor;

import java.util.Optional;

public interface BuscarProveedorPorIdUseCase {
    Optional<Proveedor> buscarPorId(Long id);
}

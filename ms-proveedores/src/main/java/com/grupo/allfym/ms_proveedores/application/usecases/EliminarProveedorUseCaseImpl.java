package com.grupo.allfym.ms_proveedores.application.usecases;

import com.grupo.allfym.ms_proveedores.domain.ports.in.EliminarProveedorUseCase;
import com.grupo.allfym.ms_proveedores.domain.ports.out.ProveedorRepositoryPort;

public class EliminarProveedorUseCaseImpl implements EliminarProveedorUseCase {

    private final ProveedorRepositoryPort proveedorRepositoryPort;

    public EliminarProveedorUseCaseImpl(ProveedorRepositoryPort proveedorRepositoryPort) {
        this.proveedorRepositoryPort = proveedorRepositoryPort;
    }

    @Override
    public void eliminar(Long id) {

    }
}

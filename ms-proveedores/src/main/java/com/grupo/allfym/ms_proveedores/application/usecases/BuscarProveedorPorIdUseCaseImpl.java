package com.grupo.allfym.ms_proveedores.application.usecases;

import com.grupo.allfym.ms_proveedores.domain.models.entities.Proveedor;
import com.grupo.allfym.ms_proveedores.domain.ports.in.BuscarProveedorPorIdUseCase;
import com.grupo.allfym.ms_proveedores.domain.ports.out.ProveedorRepositoryPort;

import java.util.Optional;

public class BuscarProveedorPorIdUseCaseImpl implements BuscarProveedorPorIdUseCase {
    private final ProveedorRepositoryPort proveedorRepositoryPort;

    public BuscarProveedorPorIdUseCaseImpl(ProveedorRepositoryPort proveedorRepositoryPort) {
        this.proveedorRepositoryPort = proveedorRepositoryPort;
    }

    @Override
    public Optional<Proveedor> buscarPorId(Long id) {
        return proveedorRepositoryPort.buscarPorId(id);
    }
}

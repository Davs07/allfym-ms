package com.grupo.allfym.ms_proveedores.application.usecases;

import com.grupo.allfym.ms_proveedores.domain.models.entities.Proveedor;
import com.grupo.allfym.ms_proveedores.domain.ports.in.GuardarProveedorUseCase;
import com.grupo.allfym.ms_proveedores.domain.ports.out.ProveedorRepositoryPort;

public class GuardarProveedorUseCaseImpl implements GuardarProveedorUseCase {
    private final ProveedorRepositoryPort proveedorRepositoryPort;

    public GuardarProveedorUseCaseImpl(ProveedorRepositoryPort proveedorRepositoryPort) {
        this.proveedorRepositoryPort = proveedorRepositoryPort;
    }

    @Override
    public Proveedor guardar(Proveedor proveedor) {
        return proveedorRepositoryPort.guardar(proveedor);
    }
}

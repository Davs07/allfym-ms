package com.grupo.allfym.ms_proveedores.application.usecases;

import com.grupo.allfym.ms_proveedores.domain.models.entities.Proveedor;
import com.grupo.allfym.ms_proveedores.domain.ports.in.ListarProveedoresUseCase;
import com.grupo.allfym.ms_proveedores.domain.ports.out.ProveedorRepositoryPort;

import java.util.List;

public class ListarProveedorUseCaseImpl implements ListarProveedoresUseCase {

    private final ProveedorRepositoryPort proveedorRepositoryPort;

    public ListarProveedorUseCaseImpl(ProveedorRepositoryPort proveedorRepositoryPort) {
        this.proveedorRepositoryPort = proveedorRepositoryPort;
    }


    @Override
    public List<Proveedor> listar() {
        return proveedorRepositoryPort.listar();
    }
}

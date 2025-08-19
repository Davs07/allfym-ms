package com.grupo.allfym.ms_proveedores.application.usecases;

import com.grupo.allfym.ms_proveedores.domain.models.entities.Proveedor;
import com.grupo.allfym.ms_proveedores.domain.models.enums.Estado;
import com.grupo.allfym.ms_proveedores.domain.ports.in.ListarProveedoresPorEstadoUseCase;
import com.grupo.allfym.ms_proveedores.domain.ports.out.ProveedorRepositoryPort;

import java.util.List;

public class ListarProveedorPorEstadoUseCaseImpl implements ListarProveedoresPorEstadoUseCase {
    private final ProveedorRepositoryPort proveedorRepositoryPort;

    public ListarProveedorPorEstadoUseCaseImpl(ProveedorRepositoryPort proveedorRepositoryPort) {
        this.proveedorRepositoryPort = proveedorRepositoryPort;
    }

    @Override
    public List<Proveedor> listarPorEstado(Estado estado) {
        return proveedorRepositoryPort.listaporEstado(estado);
    }
}

package com.grupo.allfym.ms_proveedores.application.usecases;

import com.grupo.allfym.ms_proveedores.domain.models.entities.Proveedor;
import com.grupo.allfym.ms_proveedores.domain.ports.in.ActualizarProveedorPorIdUseCase;
import com.grupo.allfym.ms_proveedores.domain.ports.out.ProveedorRepositoryPort;

import java.util.Optional;

public class ActualizarProveedorPorIdUseCaseImpl implements ActualizarProveedorPorIdUseCase {
    private final ProveedorRepositoryPort proveedorRepositoryPort;

    public ActualizarProveedorPorIdUseCaseImpl(ProveedorRepositoryPort proveedorRepositoryPort) {
        this.proveedorRepositoryPort = proveedorRepositoryPort;
    }

    @Override
    public Proveedor actualizar(Long id, Proveedor proveedor) {
        Optional<Proveedor> proveOpt = proveedorRepositoryPort.buscarPorId(id);
        if(proveOpt.isEmpty()){
            throw new IllegalArgumentException("No existe el Proveedor");
        }

        Proveedor nuevopro = new Proveedor(proveedor.getId(),
                proveedor.getNombre(),proveedor.getRuc(),proveedor.getTelefono(),
                proveedor.getEmail(),proveedor.getDireccion(),proveedor.getEstado(),
                proveedor.getFechaDeRegistro());

        return proveedorRepositoryPort.guardar(nuevopro);
    }
}

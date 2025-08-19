package com.grupo.allfym.ms_proveedores.domain.ports.out;

import com.grupo.allfym.ms_proveedores.domain.models.entities.Proveedor;
import com.grupo.allfym.ms_proveedores.domain.models.enums.Estado;

import java.util.List;
import java.util.Optional;

public interface ProveedorRepositoryPort {
    List<Proveedor> listar();
    Optional<Proveedor> buscarPorId(Long id);
    Proveedor guardar(Proveedor proveedor);
    void eliminar(Long id);
    List<Proveedor> listaporEstado(Estado estado);
}

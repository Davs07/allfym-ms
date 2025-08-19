package com.grupo.allfym.ms_proveedores.domain.ports.in;

import com.grupo.allfym.ms_proveedores.domain.models.entities.Proveedor;

public interface ActualizarProveedorPorIdUseCase {
    Proveedor actualizar(Long id, Proveedor proveedor);
}

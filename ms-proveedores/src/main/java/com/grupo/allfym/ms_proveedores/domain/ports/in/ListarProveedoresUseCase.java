package com.grupo.allfym.ms_proveedores.domain.ports.in;

import com.grupo.allfym.ms_proveedores.domain.models.entities.Proveedor;

import java.util.List;

public interface ListarProveedoresUseCase {
    List<Proveedor> listar();
}

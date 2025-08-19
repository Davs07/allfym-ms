package com.grupo.allfym.ms_proveedores.domain.ports.in;

import com.grupo.allfym.ms_proveedores.domain.models.entities.Proveedor;
import com.grupo.allfym.ms_proveedores.domain.models.enums.Estado;

import java.util.List;

public interface ListarProveedoresPorEstadoUseCase {
    List<Proveedor> listarPorEstado(Estado estado);
}

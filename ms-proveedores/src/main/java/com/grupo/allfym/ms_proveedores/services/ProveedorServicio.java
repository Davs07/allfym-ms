package com.grupo.allfym.ms_proveedores.services;


import com.grupo.allfym.ms_proveedores.models.entities.Proveedor;
import com.grupo.allfym.ms_proveedores.models.enums.Estado;

import java.util.List;
import java.util.Optional;

public interface ProveedorServicio {
    List<Proveedor> listar();
    Optional<Proveedor> buscarPorId(Long id);
    Proveedor guardar(Proveedor proveedor);
    void eliminar(Long id);
    List<Proveedor> listaporEstado(Estado estado);
}

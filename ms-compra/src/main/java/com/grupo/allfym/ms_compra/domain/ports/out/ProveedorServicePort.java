package com.grupo.allfym.ms_compra.domain.ports.out;


import com.grupo.allfym.ms_compra.domain.models.Proveedor;

import java.util.List;
import java.util.Optional;

public interface ProveedorServicePort {
    Optional<Proveedor> buscarPorId(Long id);
    List<Proveedor> listar();
}

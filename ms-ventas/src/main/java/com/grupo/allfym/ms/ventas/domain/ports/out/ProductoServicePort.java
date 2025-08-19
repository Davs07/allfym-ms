package com.grupo.allfym.ms.ventas.domain.ports.out;

import com.grupo.allfym.ms.ventas.domain.models.Producto;

import java.util.Optional;

public interface ProductoServicePort {
    
    Optional<Producto> buscarPorId(Long productoId);
    
}

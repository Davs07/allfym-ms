package com.grupo.allfym.ms.productos.domain.ports.in;

import com.grupo.allfym.ms.productos.domain.models.Producto;

import java.util.Optional;

public interface UpdateProductoUseCase {
    Optional<Producto> updateProducto(Producto productoActualizado, Long idProducto);
}

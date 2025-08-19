package com.grupo.allfym.ms.productos.domain.ports.in;

import com.grupo.allfym.ms.productos.domain.models.Producto;

import java.util.List;

public interface CreateProductoUseCase {
    Producto createProduct(Producto producto);
}
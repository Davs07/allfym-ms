package com.grupo.allfym.ms.productos.application.usecases;

import com.grupo.allfym.ms.productos.domain.models.Producto;
import com.grupo.allfym.ms.productos.domain.ports.in.UpdateProductoUseCase;
import com.grupo.allfym.ms.productos.domain.ports.out.ProductoRepositoryPort;

import java.util.Optional;

public class UpdateProductoUseCaseImpl implements UpdateProductoUseCase {

    private final ProductoRepositoryPort productoRepositoryPort;

    public UpdateProductoUseCaseImpl(ProductoRepositoryPort productoRepositoryPort) {
        this.productoRepositoryPort = productoRepositoryPort;
    }

    @Override
    public Optional<Producto> updateProducto(Producto productoActualizado, Long idProducto) {
        return productoRepositoryPort.update(productoActualizado);
    }
}

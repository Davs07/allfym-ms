package com.grupo.allfym.ms.productos.application.usecases;

import com.grupo.allfym.ms.productos.domain.models.Producto;
import com.grupo.allfym.ms.productos.domain.ports.in.CreateProductoUseCase;
import com.grupo.allfym.ms.productos.domain.ports.out.ProductoRepositoryPort;

public class CreateProductoUseCaseImpl implements CreateProductoUseCase {

    private final ProductoRepositoryPort productoRepositoryPort;

    public CreateProductoUseCaseImpl(ProductoRepositoryPort productoRepositoryPort) {
        this.productoRepositoryPort = productoRepositoryPort;
    }

    @Override
    public Producto createProduct(Producto producto) {
        return productoRepositoryPort.save(producto);
    }
}

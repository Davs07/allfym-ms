package com.grupo.allfym.ms.productos.application.usecases;

import com.grupo.allfym.ms.productos.domain.ports.in.DeleteProductoUseCase;
import com.grupo.allfym.ms.productos.domain.ports.out.ProductoRepositoryPort;

public class DeleteProductoUseCaseImpl implements DeleteProductoUseCase {

    private final ProductoRepositoryPort productoRepository;

    public DeleteProductoUseCaseImpl(ProductoRepositoryPort productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public boolean deleteProductoById(Long id) {
        return productoRepository.deleteById(id);
    }
}

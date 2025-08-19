package com.grupo.allfym.ms.productos.application.usecases;

import com.grupo.allfym.ms.productos.domain.models.Producto;
import com.grupo.allfym.ms.productos.domain.ports.in.RetrieveProductouseCase;
import com.grupo.allfym.ms.productos.domain.ports.out.ProductoRepositoryPort;
import com.grupo.allfym.ms.productos.infrastructure.entities.Categoria;
import com.grupo.allfym.ms.productos.infrastructure.entities.Marca;

import java.util.List;
import java.util.Optional;

public class RetrieveProductoUseCaseImpl implements RetrieveProductouseCase {

    private final ProductoRepositoryPort productoRepositoryPort;

    public RetrieveProductoUseCaseImpl(ProductoRepositoryPort productoRepositoryPort) {
        this.productoRepositoryPort = productoRepositoryPort;
    }

    @Override
    public Optional<Producto> getProductById(Long id) {
        return productoRepositoryPort.findById(id);
    }

    @Override
    public List<Producto> getAllProducts() {
        return productoRepositoryPort.findAll();
    }

    @Override
    public List<Producto> findByCategoria(Categoria categoria) {
        return productoRepositoryPort.findByCategoria(categoria);
    }

    @Override
    public List<Producto> findByMarca(Marca marca) {
        return productoRepositoryPort.findByMarca(marca);
    }
}

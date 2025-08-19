package com.grupo.allfym.ms.productos.application.services;

import com.grupo.allfym.ms.productos.domain.models.Producto;
import com.grupo.allfym.ms.productos.domain.ports.in.CreateProductoUseCase;
import com.grupo.allfym.ms.productos.domain.ports.in.DeleteProductoUseCase;
import com.grupo.allfym.ms.productos.domain.ports.in.RetrieveProductouseCase;
import com.grupo.allfym.ms.productos.domain.ports.in.UpdateProductoUseCase;
import com.grupo.allfym.ms.productos.infrastructure.entities.Categoria;
import com.grupo.allfym.ms.productos.infrastructure.entities.Marca;

import java.util.List;
import java.util.Optional;

public class ProductoService implements CreateProductoUseCase, DeleteProductoUseCase, RetrieveProductouseCase, UpdateProductoUseCase {

    private final CreateProductoUseCase createProductoUseCase;
    private final DeleteProductoUseCase deleteProductoUseCase;
    private final RetrieveProductouseCase retrieveProductouseCase;
    private final UpdateProductoUseCase updateProductoUseCase;

    public ProductoService(CreateProductoUseCase createProductoUseCase, DeleteProductoUseCase deleteProductoUseCase, RetrieveProductouseCase retrieveProductouseCase, UpdateProductoUseCase updateProductoUseCase) {
        this.createProductoUseCase = createProductoUseCase;
        this.deleteProductoUseCase = deleteProductoUseCase;
        this.retrieveProductouseCase = retrieveProductouseCase;
        this.updateProductoUseCase = updateProductoUseCase;
    }

    @Override
    public Producto createProduct(Producto producto) {
        return createProductoUseCase.createProduct(producto);
    }

    @Override
    public boolean deleteProductoById(Long id) {
        return deleteProductoUseCase.deleteProductoById(id);
    }

    @Override
    public Optional<Producto> getProductById(Long id) {
        return retrieveProductouseCase.getProductById(id);
    }

    @Override
    public List<Producto> getAllProducts() {
        return retrieveProductouseCase.getAllProducts();
    }

    @Override
    public List<Producto> findByCategoria(Categoria categoria) {
        return retrieveProductouseCase.findByCategoria(categoria);
    }

    @Override
    public List<Producto> findByMarca(Marca marca) {
        return retrieveProductouseCase.findByMarca(marca);
    }


    @Override
    public Optional<Producto> updateProducto(Producto productoActualizado, Long idProducto) {
        return updateProductoUseCase.updateProducto(productoActualizado, idProducto);
    }
}

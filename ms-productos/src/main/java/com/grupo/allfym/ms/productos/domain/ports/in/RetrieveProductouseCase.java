package com.grupo.allfym.ms.productos.domain.ports.in;

import com.grupo.allfym.ms.productos.domain.models.Producto;
import com.grupo.allfym.ms.productos.infrastructure.entities.Categoria;
import com.grupo.allfym.ms.productos.infrastructure.entities.Marca;

import java.util.List;
import java.util.Optional;

public interface RetrieveProductouseCase {
    Optional<Producto> getProductById(Long id);
    List<Producto> getAllProducts();
    List<Producto> findByCategoria(Categoria categoria);
    List<Producto> findByMarca(Marca marca);
}

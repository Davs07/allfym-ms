package com.grupo.allfym.ms.productos.domain.ports.out;

import com.grupo.allfym.ms.productos.domain.models.Producto;
import com.grupo.allfym.ms.productos.infrastructure.entities.Categoria;
import com.grupo.allfym.ms.productos.infrastructure.entities.Marca;

import java.util.List;
import java.util.Optional;

public interface ProductoRepositoryPort {
    Producto save(Producto producto);
    List<Producto> findAll();
    Optional<Producto> findById(Long id);
    List<Producto> findByCategoria(Categoria categoria);
    List<Producto> findByMarca(Marca marca);
    Optional<Producto> update(Producto producto);
    boolean deleteById(Long id);
}

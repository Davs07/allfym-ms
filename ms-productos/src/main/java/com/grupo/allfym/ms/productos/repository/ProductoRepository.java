package com.grupo.allfym.ms.productos.repository;

import com.grupo.allfym.ms.productos.models.entity.Categoria;
import com.grupo.allfym.ms.productos.models.entity.Marca;
import com.grupo.allfym.ms.productos.models.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

    Optional<Producto> findByNombre(String nombre);

    List<Producto> findByCategoria(Categoria categoria);

    List<Producto> findByMarca(Marca marca);
}

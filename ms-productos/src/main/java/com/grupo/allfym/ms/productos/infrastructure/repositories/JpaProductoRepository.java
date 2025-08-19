package com.grupo.allfym.ms.productos.infrastructure.repositories;

import com.grupo.allfym.ms.productos.domain.models.Producto;
import com.grupo.allfym.ms.productos.infrastructure.entities.Categoria;
import com.grupo.allfym.ms.productos.infrastructure.entities.Marca;
import com.grupo.allfym.ms.productos.infrastructure.entities.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface JpaProductoRepository extends JpaRepository<ProductoEntity, Long> {

    List<ProductoEntity> findByCategoria(Categoria categoria);
    List<ProductoEntity> findByMarca(Marca marca);
}

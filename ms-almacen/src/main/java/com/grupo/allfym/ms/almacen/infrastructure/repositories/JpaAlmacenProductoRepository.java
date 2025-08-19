package com.grupo.allfym.ms.almacen.infrastructure.repositories;

import com.grupo.allfym.ms.almacen.infrastructure.entities.AlmacenProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaAlmacenProductoRepository extends JpaRepository<AlmacenProductoEntity, Long> {
    Optional<AlmacenProductoEntity> findByIdAlmacen(Long idAlmacen); // ← CORREGIDO
    boolean existsByIdProducto(Long idProducto);
}

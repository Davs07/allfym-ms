package com.grupo.allfym.ms_compra.infrastucture.repositories;

import com.grupo.allfym.ms.compra.domain.models.enums.Estado;
import com.grupo.allfym.ms.compra.infrastucture.entities.CompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaCompraRepository extends JpaRepository<CompraEntity, Long> {
    List<CompraEntity> findByEstado(Estado estado);
    List<CompraEntity> findByIdProveedor(Long idProveedor);
}

package com.grupo.allfym.ms_proveedores.infrastructure.repositories;

import com.grupo.allfym.ms_proveedores.infrastructure.entities.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaProveedorRepository extends JpaRepository<ProveedorEntity,Long> {
    List<ProveedorEntity> findByEstado(com.grupo.allfym.ms_proveedores.domain.models.enums.Estado estado);
}

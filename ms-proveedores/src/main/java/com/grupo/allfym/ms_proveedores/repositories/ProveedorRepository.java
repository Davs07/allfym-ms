package com.grupo.allfym.ms_proveedores.repositories;

import com.grupo.allfym.ms_proveedores.models.entities.Proveedor;
import com.grupo.allfym.ms_proveedores.models.enums.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,Long> {
    List<Proveedor> findByEstado(Estado estado);
    Optional<Proveedor> findByRUC(String ruc);
}

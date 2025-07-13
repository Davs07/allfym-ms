package com.grupo.allfym.ms_proveedores.repositories;

import com.grupo.allfym.ms_proveedores.models.entities.Proveedor;
import com.grupo.allfym.ms_proveedores.models.enums.Estado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository extends CrudRepository<Proveedor,Long> {
    List<Proveedor> findByEstado(com.grupo.allfym.ms_proveedores.models.enums.Estado estado);
}

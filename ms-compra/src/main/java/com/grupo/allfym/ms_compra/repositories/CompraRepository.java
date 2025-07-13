package com.grupo.allfym.ms_compra.repositories;

import com.grupo.allfym.ms_compra.models.entities.Compra;
import com.grupo.allfym.ms_compra.models.enums.Estado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends CrudRepository<Compra, Long> {
    List<Compra> findByEstado(Estado estado);
    List<Compra> findByIdProveedor(Long idProveedor);
}

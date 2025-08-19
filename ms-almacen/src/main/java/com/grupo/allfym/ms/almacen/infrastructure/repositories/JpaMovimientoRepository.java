package com.grupo.allfym.ms.almacen.infrastructure.repositories;

import com.grupo.allfym.ms.almacen.infrastructure.entities.MovimientoEntity;
import com.grupo.allfym.ms.almacen.infrastructure.entities.TipoMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface JpaMovimientoRepository extends JpaRepository<MovimientoEntity, Long> {
    List<MovimientoEntity> findByTipoMovimiento(TipoMovimiento tipoMovimiento);
}

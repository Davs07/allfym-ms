package com.grupo.allfym.ms.pagos.repositories;

import com.grupo.allfym.ms.pagos.entity.Pago;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository extends CrudRepository<Pago,Long> {
    List<Pago> findByVentaId(Long ventaId);
}

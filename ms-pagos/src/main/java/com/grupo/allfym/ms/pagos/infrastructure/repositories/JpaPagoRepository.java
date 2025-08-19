package com.grupo.allfym.ms.pagos.infrastructure.repositories;

import com.grupo.allfym.ms.pagos.infrastructure.entities.PagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPagoRepository extends JpaRepository<PagoEntity,Long> {
    //List<PagoEntity> findByMetodo(String metodo);
}

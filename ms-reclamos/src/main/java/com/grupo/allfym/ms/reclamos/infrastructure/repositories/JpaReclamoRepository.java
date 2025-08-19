package com.grupo.allfym.ms.reclamos.infrastructure.repositories;

import com.grupo.allfym.ms.reclamos.infrastructure.entities.ReclamoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaReclamoRepository extends JpaRepository<ReclamoEntity,Long> {

}

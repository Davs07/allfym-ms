package com.grupo.allfym.ms.ventas.infrastructure.repositories;

import com.grupo.allfym.ms.ventas.domain.models.enums.EstadoVenta;
import com.grupo.allfym.ms.ventas.infrastructure.entities.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JpaVentaRepository extends JpaRepository<VentaEntity, Long> {

    List<VentaEntity> findByClienteId(Long clienteId);

    List<VentaEntity> findByEstado(EstadoVenta estado);

    @Query("SELECT v FROM VentaEntity v WHERE v.fechaRegistro BETWEEN :fechaInicio AND :fechaFin")
    List<VentaEntity> findByFechaRegistroBetween(@Param("fechaInicio") LocalDateTime fechaInicio,
                                                @Param("fechaFin") LocalDateTime fechaFin);

    @Query("SELECT v FROM VentaEntity v WHERE v.clienteId = :clienteId AND v.estado = :estado")
    List<VentaEntity> findByClienteIdAndEstado(@Param("clienteId") Long clienteId,
                                              @Param("estado") EstadoVenta estado);
}

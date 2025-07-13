package com.grupo.allfym.ms.ventas.repositories;

import com.grupo.allfym.ms.ventas.entity.Venta;
import com.grupo.allfym.ms.ventas.enums.EstadoVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByClienteId(Long clienteId);

    List<Venta> findByEstado(EstadoVenta estado);

    @Query("SELECT v FROM Venta v WHERE v.fechaRegistro.fechaRegistro BETWEEN :fechaInicio AND :fechaFin")
    List<Venta> findByFechaRegistroBetween(@Param("fechaInicio") LocalDateTime fechaInicio,
                                           @Param("fechaFin") LocalDateTime fechaFin);

    @Query("SELECT v FROM Venta v WHERE v.clienteId = :clienteId AND v.estado = :estado")
    List<Venta> findByClienteIdAndEstado(@Param("clienteId") Long clienteId,
                                         @Param("estado") EstadoVenta estado);
}

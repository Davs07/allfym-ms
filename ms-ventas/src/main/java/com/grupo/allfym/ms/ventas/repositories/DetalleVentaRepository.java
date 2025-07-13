package com.grupo.allfym.ms.ventas.repositories;

import com.grupo.allfym.ms.ventas.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    List<DetalleVenta> findByVentaId(Long ventaId);

    List<DetalleVenta> findByProductoContainingIgnoreCase(String producto);

    @Query("SELECT dv FROM DetalleVenta dv WHERE dv.venta.id = :ventaId ORDER BY dv.id")
    List<DetalleVenta> findByVentaIdOrderById(@Param("ventaId") Long ventaId);
}

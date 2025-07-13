package com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.repositories;

import com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.models.entities.ReclamoProveedor;
import com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.models.enums.EstadoReclamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReclamoProveedorRepository extends JpaRepository<ReclamoProveedor, Long> {

    // === MÉTODOS DE DOMINIO IMPLEMENTADOS ===

    /**
     * FiltrarPorEstado() - Buscar reclamos por estado
     */
    List<ReclamoProveedor> findByEstadoReclamo(EstadoReclamo estadoReclamo);

    /**
     * FiltrarPorIdproveedor() - Buscar reclamos por ID de proveedor
     */
    List<ReclamoProveedor> findByIdProveedor(Long idProveedor);

    /**
     * Buscar por número de orden
     */
    Optional<ReclamoProveedor> findByNroOrden(String nroOrden);

    /**
     * Buscar reclamos por proveedor y estado
     */
    List<ReclamoProveedor> findByIdProveedorAndEstadoReclamo(Long idProveedor, EstadoReclamo estadoReclamo);

    /**
     * Buscar reclamos por fecha de creación
     */
    @Query("SELECT r FROM ReclamoProveedor r WHERE r.fechaCreacion.fecha = :fecha")
    List<ReclamoProveedor> findByFechaCreacion(@Param("fecha") LocalDate fecha);

    /**
     * Buscar reclamos pendientes por proveedor
     */
    @Query("SELECT r FROM ReclamoProveedor r WHERE r.idProveedor = :idProveedor AND r.estadoReclamo = 'PENDIENTE'")
    List<ReclamoProveedor> findReclamosPendientesByProveedor(@Param("idProveedor") Long idProveedor);

    /**
     * Contar reclamos por estado
     */
    Long countByEstadoReclamo(EstadoReclamo estadoReclamo);

    /**
     * Contar reclamos por proveedor
     */
    Long countByIdProveedor(Long idProveedor);
}

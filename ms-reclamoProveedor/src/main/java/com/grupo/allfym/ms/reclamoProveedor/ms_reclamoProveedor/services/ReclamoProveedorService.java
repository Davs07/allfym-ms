package com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.services;

import com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.models.dto.ReclamoProveedorRequestDTO;
import com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.models.dto.ReclamoProveedorResponseDTO;
import com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.models.entities.ReclamoProveedor;
import com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.models.enums.EstadoReclamo;

import java.util.List;
import java.util.Optional;

public interface ReclamoProveedorService {

    // === MÉTODOS OPTIMIZADOS CON DTOs ===
    ReclamoProveedorResponseDTO crearReclamo(ReclamoProveedorRequestDTO reclamoRequest);
    Optional<ReclamoProveedorResponseDTO> obtenerReclamoPorId(Long id);
    List<ReclamoProveedorResponseDTO> obtenerTodosLosReclamos();
    ReclamoProveedorResponseDTO actualizarReclamo(Long id, ReclamoProveedorRequestDTO reclamoRequest);
    void eliminarReclamo(Long id);
    ReclamoProveedorResponseDTO cambiarEstadoReclamo(Long id, String nuevoEstado);

    // === MÉTODOS DE DOMINIO ===
    /**
     * reclamo() - Crear un nuevo reclamo básico
     */
    ReclamoProveedorResponseDTO reclamo(String descripcion, Long idProveedor, String nroOrden);

    /**
     * agregarReclamo() - Agregar información adicional a un reclamo existente
     */
    ReclamoProveedorResponseDTO agregarReclamo(Long idReclamo, String descripcionAdicional);

    /**
     * FiltrarPorEstado() - Filtrar reclamos por estado
     */
    List<ReclamoProveedorResponseDTO> filtrarPorEstado(String estado);

    /**
     * FiltrarPorIdproveedor() - Filtrar reclamos por ID de proveedor
     */
    List<ReclamoProveedorResponseDTO> filtrarPorIdProveedor(Long idProveedor);

    // === MÉTODOS ADICIONALES ===
    List<ReclamoProveedorResponseDTO> obtenerReclamosPorProveedorYEstado(Long idProveedor, String estado);
    List<ReclamoProveedorResponseDTO> obtenerReclamosPendientesPorProveedor(Long idProveedor);
    Optional<ReclamoProveedorResponseDTO> buscarPorNroOrden(String nroOrden);

    // === MÉTODOS LEGACY (Para compatibilidad) ===
    List<ReclamoProveedor> listar();
    Optional<ReclamoProveedor> buscarPorId(Long id);
    ReclamoProveedor guardar(ReclamoProveedor reclamo);
    void eliminar(Long id);
}

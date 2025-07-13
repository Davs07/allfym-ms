package com.grupo.allfym.ms_proveedores.services;

import com.grupo.allfym.ms_proveedores.models.dto.ProveedorRequestDTO;
import com.grupo.allfym.ms_proveedores.models.dto.ProveedorResponseDTO;
import com.grupo.allfym.ms_proveedores.models.entities.Proveedor;
import com.grupo.allfym.ms_proveedores.models.enums.Estado;

import java.util.List;
import java.util.Optional;

public interface ProveedorServicio {

    // === MÉTODOS OPTIMIZADOS CON DTOs ===
    ProveedorResponseDTO crearProveedor(ProveedorRequestDTO proveedorRequest);
    Optional<ProveedorResponseDTO> obtenerProveedorPorId(Long id);
    List<ProveedorResponseDTO> obtenerTodosLosProveedores();
    List<ProveedorResponseDTO> obtenerProveedoresPorEstado(String estado);
    ProveedorResponseDTO actualizarProveedor(Long id, ProveedorRequestDTO proveedorRequest);
    void eliminarProveedor(Long id);
    Optional<ProveedorResponseDTO> buscarPorRuc(String ruc);

    // === MÉTODOS LEGACY (Compatibilidad) ===
    List<Proveedor> listar();
    Optional<Proveedor> buscarPorId(Long id);
    Proveedor guardar(Proveedor proveedor);
    void eliminar(Long id);
    List<Proveedor> listaporEstado(Estado estado);
}

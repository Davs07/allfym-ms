package com.grupo.allfym.ms_compra.services;

import com.grupo.allfym.ms_compra.models.dto.CompraRequestDTO;
import com.grupo.allfym.ms_compra.models.dto.CompraResponseDTO;
import com.grupo.allfym.ms_compra.models.entities.Compra;
import com.grupo.allfym.ms_compra.models.enums.Estado;

import java.util.List;
import java.util.Optional;

public interface CompraService {

    // === MÉTODOS OPTIMIZADOS CON DTOs ===
    CompraResponseDTO crearCompra(CompraRequestDTO compraRequest);
    Optional<CompraResponseDTO> obtenerCompraPorId(Long id);
    List<CompraResponseDTO> obtenerTodasLasCompras();
    List<CompraResponseDTO> obtenerComprasPorProveedor(Long proveedorId);
    CompraResponseDTO actualizarEstadoCompra(Long id, String nuevoEstado);

    // === MÉTODOS LEGACY (Compatibilidad) ===
    Compra guardar(Compra compra);
    void eliminar(Long id);
    List<Compra> listar();
    Optional<Compra> buscarPorId(Long idCompra);
    List<Compra> buscarPorEstado(Estado estado);
    List<Compra> buscarPorIdProveedor(Long idProveedor);
    List<Compra> buscarPorNombreProveedor(String nombre);
    void cambiarEstado(Long id, String estado);
}

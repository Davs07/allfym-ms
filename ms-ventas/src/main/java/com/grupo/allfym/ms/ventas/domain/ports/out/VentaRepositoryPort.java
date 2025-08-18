package com.grupo.allfym.ms.ventas.domain.ports.out;

import com.grupo.allfym.ms.ventas.domain.models.entities.Venta;
import com.grupo.allfym.ms.ventas.domain.models.enums.EstadoVenta;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida para el repositorio de ventas.
 * Define el contrato para la persistencia de ventas.
 */
public interface VentaRepositoryPort {
    
    /**
     * Guarda una venta en el repositorio.
     * 
     * @param venta Venta a guardar
     * @return La venta guardada con su ID asignado
     */
    Venta guardar(Venta venta);
    
    /**
     * Busca una venta por su ID.
     * 
     * @param id ID de la venta
     * @return La venta encontrada o empty si no existe
     */
    Optional<Venta> buscarPorId(Long id);
    
    /**
     * Obtiene todas las ventas.
     * 
     * @return Lista de todas las ventas
     */
    List<Venta> buscarTodas();
    
    /**
     * Busca ventas por cliente.
     * 
     * @param clienteId ID del cliente
     * @return Lista de ventas del cliente
     */
    List<Venta> buscarPorClienteId(Long clienteId);
    
    /**
     * Busca ventas por estado.
     * 
     * @param estado Estado de la venta
     * @return Lista de ventas con el estado especificado
     */
    List<Venta> buscarPorEstado(EstadoVenta estado);
    
    /**
     * Busca ventas en un rango de fechas.
     * 
     * @param fechaInicio Fecha de inicio del rango
     * @param fechaFin Fecha de fin del rango
     * @return Lista de ventas en el rango de fechas
     */
    List<Venta> buscarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    
    /**
     * Busca ventas por cliente y estado.
     * 
     * @param clienteId ID del cliente
     * @param estado Estado de la venta
     * @return Lista de ventas que cumplen ambos criterios
     */
    List<Venta> buscarPorClienteIdYEstado(Long clienteId, EstadoVenta estado);
    
    /**
     * Elimina una venta por su ID.
     * 
     * @param id ID de la venta a eliminar
     */
    void eliminarPorId(Long id);
    
    /**
     * Verifica si existe una venta con el ID especificado.
     * 
     * @param id ID de la venta
     * @return true si existe, false en caso contrario
     */
    boolean existePorId(Long id);
}

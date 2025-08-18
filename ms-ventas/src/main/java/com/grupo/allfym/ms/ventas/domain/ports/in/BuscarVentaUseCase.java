package com.grupo.allfym.ms.ventas.domain.ports.in;

import com.grupo.allfym.ms.ventas.domain.models.entities.Venta;
import com.grupo.allfym.ms.ventas.domain.models.enums.EstadoVenta;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Puerto de entrada para buscar ventas.
 * Define el contrato para los casos de uso de consulta de ventas.
 */
public interface BuscarVentaUseCase {
    
    /**
     * Busca una venta por su ID.
     * 
     * @param id ID de la venta
     * @return La venta encontrada o empty si no existe
     */
    Optional<Venta> buscarPorId(Long id);
    
    /**
     * Obtiene todas las ventas del sistema.
     * 
     * @return Lista de todas las ventas
     */
    List<Venta> obtenerTodas();
    
    /**
     * Busca ventas por cliente.
     * 
     * @param clienteId ID del cliente
     * @return Lista de ventas del cliente
     */
    List<Venta> buscarPorCliente(Long clienteId);
    
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
    List<Venta> buscarPorClienteYEstado(Long clienteId, EstadoVenta estado);
}

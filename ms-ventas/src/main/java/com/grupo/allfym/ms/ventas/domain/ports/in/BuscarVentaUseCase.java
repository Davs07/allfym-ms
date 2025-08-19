package com.grupo.allfym.ms.ventas.domain.ports.in;

import com.grupo.allfym.ms.ventas.domain.models.entities.Venta;
import com.grupo.allfym.ms.ventas.domain.models.enums.EstadoVenta;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BuscarVentaUseCase {
    
    Optional<Venta> buscarPorId(Long id);
    
    List<Venta> obtenerTodas();
    
    List<Venta> buscarPorCliente(Long clienteId);
    
    List<Venta> buscarPorEstado(EstadoVenta estado);
    
    List<Venta> buscarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);
 
}

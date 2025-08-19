package com.grupo.allfym.ms.ventas.domain.ports.out;

import com.grupo.allfym.ms.ventas.domain.models.entities.Venta;
import com.grupo.allfym.ms.ventas.domain.models.enums.EstadoVenta;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VentaRepositoryPort {
    
    Venta guardar(Venta venta);
    
    Optional<Venta> buscarPorId(Long id);

    List<Venta> buscarTodas();
    
    List<Venta> buscarPorClienteId(Long clienteId);

    List<Venta> buscarPorEstado(EstadoVenta estado);
 
    List<Venta> buscarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    void eliminarPorId(Long id);

}

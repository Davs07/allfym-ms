package com.grupo.allfym.ms.ventas.application.usecases;

import com.grupo.allfym.ms.ventas.domain.models.entities.Venta;
import com.grupo.allfym.ms.ventas.domain.models.enums.EstadoVenta;
import com.grupo.allfym.ms.ventas.domain.ports.in.BuscarVentaUseCase;
import com.grupo.allfym.ms.ventas.domain.ports.out.VentaRepositoryPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Implementación para buscar ventas.
 */
@Component
@Transactional(readOnly = true)
public class BuscarVentaUseCaseImpl implements BuscarVentaUseCase {

    private final VentaRepositoryPort ventaRepository;

    public BuscarVentaUseCaseImpl(VentaRepositoryPort ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public Optional<Venta> buscarPorId(Long id) {
        return ventaRepository.buscarPorId(id);
    }

    @Override
    public List<Venta> obtenerTodas() {
        return ventaRepository.buscarTodas();
    }

    @Override
    public List<Venta> buscarPorCliente(Long clienteId) {
        return ventaRepository.buscarPorClienteId(clienteId);
    }

    @Override
    public List<Venta> buscarPorEstado(EstadoVenta estado) {
        return ventaRepository.buscarPorEstado(estado);
    }

    @Override
    public List<Venta> buscarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return ventaRepository.buscarPorRangoFechas(fechaInicio, fechaFin);
    }

    @Override
    public List<Venta> buscarPorClienteYEstado(Long clienteId, EstadoVenta estado) {
        return ventaRepository.buscarPorClienteIdYEstado(clienteId, estado);
    }
}

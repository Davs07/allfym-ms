package com.grupo.allfym.ms.ventas.application.services;

import com.grupo.allfym.ms.ventas.domain.models.entities.Venta;
import com.grupo.allfym.ms.ventas.domain.models.enums.EstadoVenta;
import com.grupo.allfym.ms.ventas.domain.ports.in.CrearVentaUseCase;
import com.grupo.allfym.ms.ventas.domain.ports.in.BuscarVentaUseCase;
import com.grupo.allfym.ms.ventas.domain.ports.in.GestionarVentaUseCase;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VentaApplicationService {

    private final CrearVentaUseCase crearVentaUseCase;
    private final BuscarVentaUseCase buscarVentaUseCase;
    private final GestionarVentaUseCase gestionarVentaUseCase;

    public VentaApplicationService(CrearVentaUseCase crearVentaUseCase,
                                   BuscarVentaUseCase buscarVentaUseCase,
                                   GestionarVentaUseCase gestionarVentaUseCase) {
        this.crearVentaUseCase = crearVentaUseCase;
        this.buscarVentaUseCase = buscarVentaUseCase;
        this.gestionarVentaUseCase = gestionarVentaUseCase;
    }

    public Venta crearVenta(Venta venta) {
        return crearVentaUseCase.crear(venta);
    }

    public Optional<Venta> buscarPorId(Long id) {
        return buscarVentaUseCase.buscarPorId(id);
    }

    public List<Venta> obtenerTodasLasVentas() {
        return buscarVentaUseCase.obtenerTodas();
    }

    public List<Venta> buscarPorClienteId(Long clienteId) {
        return buscarVentaUseCase.buscarPorCliente(clienteId);
    }

    public List<Venta> buscarPorEstado(EstadoVenta estado) {
        return buscarVentaUseCase.buscarPorEstado(estado);
    }

    public List<Venta> buscarPorFechaRegistro(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return buscarVentaUseCase.buscarPorRangoFechas(fechaInicio, fechaFin);
    }

    public Venta confirmarVenta(Long ventaId) {
        return gestionarVentaUseCase.confirmarVenta(ventaId);
    }

    public Venta cancelarVenta(Long ventaId) {
        return gestionarVentaUseCase.cancelarVenta(ventaId);
    }

    public Venta marcarComoEntregada(Long ventaId) {
        return gestionarVentaUseCase.marcarComoEntregada(ventaId);
    }

    public void eliminarVenta(Long id) {
        gestionarVentaUseCase.eliminarVenta(id);
    }
}

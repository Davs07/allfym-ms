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

/**
 * Servicio de aplicación para ventas.
 * Actúa como facade entre la infraestructura y los casos de uso.
 * Mantiene la misma interfaz que el VentaService original para facilitar migración.
 */
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

    /**
     * Crea una nueva venta.
     */
    public Venta crearVenta(CrearVentaUseCase.CrearVentaCommand command) {
        return crearVentaUseCase.crear(command);
    }

    /**
     * Busca una venta por ID.
     */
    public Optional<Venta> buscarPorId(Long id) {
        return buscarVentaUseCase.buscarPorId(id);
    }

    /**
     * Obtiene todas las ventas.
     */
    public List<Venta> obtenerTodasLasVentas() {
        return buscarVentaUseCase.obtenerTodas();
    }

    /**
     * Busca ventas por cliente.
     */
    public List<Venta> buscarPorClienteId(Long clienteId) {
        return buscarVentaUseCase.buscarPorCliente(clienteId);
    }

    /**
     * Busca ventas por estado.
     */
    public List<Venta> buscarPorEstado(EstadoVenta estado) {
        return buscarVentaUseCase.buscarPorEstado(estado);
    }

    /**
     * Busca ventas por rango de fechas.
     */
    public List<Venta> buscarPorFechaRegistro(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return buscarVentaUseCase.buscarPorRangoFechas(fechaInicio, fechaFin);
    }

    /**
     * Confirma una venta.
     */
    public Venta confirmarVenta(Long ventaId) {
        return gestionarVentaUseCase.confirmarVenta(ventaId);
    }

    /**
     * Cancela una venta.
     */
    public Venta cancelarVenta(Long ventaId) {
        return gestionarVentaUseCase.cancelarVenta(ventaId);
    }

    /**
     * Marca una venta como entregada.
     */
    public Venta marcarComoEntregada(Long ventaId) {
        return gestionarVentaUseCase.marcarComoEntregada(ventaId);
    }

    /**
     * Elimina una venta.
     */
    public void eliminarVenta(Long id) {
        gestionarVentaUseCase.eliminarVenta(id);
    }
}

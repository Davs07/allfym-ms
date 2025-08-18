package com.grupo.allfym.ms.ventas.domain.models.entities;

import com.grupo.allfym.ms.ventas.domain.models.enums.EstadoVenta;
import com.grupo.allfym.ms.ventas.domain.models.enums.MetodoPago;
import com.grupo.allfym.ms.ventas.domain.models.vo.FechaRegistro;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad de dominio Venta - POJO puro sin dependencias de infraestructura
 */
public class Venta {

    private Long id;
    private Long clienteId;
    private FechaRegistro fechaRegistro;
    private MetodoPago metodoPago;
    private EstadoVenta estado;
    private BigDecimal total;
    private List<DetalleVenta> detalles = new ArrayList<>();

    public Venta() {
    }

    public Venta(Long clienteId, MetodoPago metodoPago) {
        this.fechaRegistro = new FechaRegistro();
        this.estado = EstadoVenta.PENDIENTE;
        this.total = BigDecimal.ZERO;
        this.clienteId = clienteId;
        this.metodoPago = metodoPago;
    }

    // Métodos de dominio - lógica de negocio pura
    public void agregarDetalle(DetalleVenta detalle) {
        detalle.setVenta(this);
        this.detalles.add(detalle);
        calcularTotal();
    }

    public void calcularTotal() {
        this.total = detalles.stream()
                .map(DetalleVenta::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void confirmarVenta() {
        if (this.estado == EstadoVenta.PENDIENTE && !detalles.isEmpty()) {
            this.estado = EstadoVenta.CONFIRMADA;
        }
    }

    public void cancelarVenta() {
        if (this.estado == EstadoVenta.PENDIENTE) {
            this.estado = EstadoVenta.CANCELADA;
        }
    }

    public void marcarComoEntregada() {
        if (this.estado == EstadoVenta.CONFIRMADA) {
            this.estado = EstadoVenta.ENTREGADA;
        }
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public FechaRegistro getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(FechaRegistro fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public EstadoVenta getEstado() {
        return estado;
    }

    public void setEstado(EstadoVenta estado) {
        this.estado = estado;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venta venta = (Venta) o;
        return id != null ? id.equals(venta.id) : venta.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", estado=" + estado +
                ", total=" + total +
                '}';
    }
}
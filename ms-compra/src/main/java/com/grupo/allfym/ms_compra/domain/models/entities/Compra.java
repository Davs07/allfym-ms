package com.grupo.allfym.ms_compra.domain.models.entities;

import com.grupo.allfym.ms_compra.domain.models.enums.Estado;
import com.grupo.allfym.ms.compra.domain.models.vo.Fecha;

import java.util.List;

public class Compra {
    private final Long id;
    private final Fecha fechaEmision;
    private final Fecha fechaRecepcion;
    private final Estado estado;
    private final Long idProveedor;
    private final Double montoTotal;
    private final String observaciones;
    private final List<DetalleCompra> detalles;

    public Compra(Long id, Fecha fechaEmision, Fecha fechaRecepcion, Estado estado,
                  Long idProveedor, Double montoTotal, String observaciones,
                  List<DetalleCompra> detalles) {
        if (idProveedor == null) {
            throw new IllegalArgumentException("El ID del proveedor no puede ser nulo");
        }
        if (detalles == null || detalles.isEmpty()) {
            throw new IllegalArgumentException("La compra debe tener al menos un detalle");
        }

        this.id = id;
        this.fechaEmision = (fechaEmision != null) ? fechaEmision : new Fecha();
        this.fechaRecepcion = fechaRecepcion;
        this.estado = (estado != null) ? estado : Estado.PENDIENTE;
        this.idProveedor = idProveedor;
        this.montoTotal = (montoTotal != null) ? montoTotal : 0.0;
        this.observaciones = observaciones;
        this.detalles = detalles;
    }

    public Long getId() { return id; }
    public Fecha getFechaEmision() { return fechaEmision; }
    public Fecha getFechaRecepcion() { return fechaRecepcion; }
    public Estado getEstado() { return estado; }
    public Long getIdProveedor() { return idProveedor; }
    public Double getMontoTotal() { return montoTotal; }
    public String getObservaciones() { return observaciones; }
    public List<DetalleCompra> getDetalles() { return detalles; }

    public Compra cambiarEstado(Estado nuevoEstado) {
        return new Compra(this.id, this.fechaEmision, this.fechaRecepcion,
                nuevoEstado, this.idProveedor, this.montoTotal,
                this.observaciones, this.detalles);
    }

    public Compra agregarDetalle(DetalleCompra detalle) {
        List<DetalleCompra> nuevosDetalles = new java.util.ArrayList<>(this.detalles);
        nuevosDetalles.add(detalle);

        double nuevoTotal = nuevosDetalles.stream()
                .mapToDouble(d -> d.getSubtotal())
                .sum();//DetalleCompra::getSubtotal, en cada objeto lo convierte en un double y usa el valor de get

        return new Compra(this.id, this.fechaEmision, this.fechaRecepcion,
                this.estado, this.idProveedor, nuevoTotal,
                this.observaciones, nuevosDetalles);
    }
}

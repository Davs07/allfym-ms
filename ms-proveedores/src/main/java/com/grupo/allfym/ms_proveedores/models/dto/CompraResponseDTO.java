package com.grupo.allfym.ms_proveedores.models.dto;

import java.time.LocalDateTime;

public class CompraResponseDTO {

    private Long id;
    private Long proveedorId;
    private String nombreProveedor;
    private LocalDateTime fechaEmision;
    private LocalDateTime fechaRecepcion;
    private String estado;
    private Double montoTotal;
    private String observaciones;

    public CompraResponseDTO() {
    }

    public CompraResponseDTO(Long id, Long proveedorId, String nombreProveedor, LocalDateTime fechaEmision,
                            LocalDateTime fechaRecepcion, String estado, Double montoTotal, String observaciones) {
        this.id = id;
        this.proveedorId = proveedorId;
        this.nombreProveedor = nombreProveedor;
        this.fechaEmision = fechaEmision;
        this.fechaRecepcion = fechaRecepcion;
        this.estado = estado;
        this.montoTotal = montoTotal;
        this.observaciones = observaciones;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Long proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public LocalDateTime getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(LocalDateTime fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}

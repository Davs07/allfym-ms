package com.grupo.allfym.ms_compra.models.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CompraResponseDTO {

    private Long id;
    private Long proveedorId;
    private String nombreProveedor;
    private String rucProveedor;
    private String emailProveedor;
    private LocalDateTime fechaEmision;
    private LocalDateTime fechaRecepcion;
    private String estado;
    private Double montoTotal;
    private String observaciones;
    private List<DetalleCompraResponseDTO> detalles;

    public CompraResponseDTO() {
    }

    public CompraResponseDTO(Long id, Long proveedorId, String nombreProveedor, String rucProveedor,
                            String emailProveedor, LocalDateTime fechaEmision, LocalDateTime fechaRecepcion,
                            String estado, Double montoTotal, String observaciones,
                            List<DetalleCompraResponseDTO> detalles) {
        this.id = id;
        this.proveedorId = proveedorId;
        this.nombreProveedor = nombreProveedor;
        this.rucProveedor = rucProveedor;
        this.emailProveedor = emailProveedor;
        this.fechaEmision = fechaEmision;
        this.fechaRecepcion = fechaRecepcion;
        this.estado = estado;
        this.montoTotal = montoTotal;
        this.observaciones = observaciones;
        this.detalles = detalles;
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

    public String getRucProveedor() {
        return rucProveedor;
    }

    public void setRucProveedor(String rucProveedor) {
        this.rucProveedor = rucProveedor;
    }

    public String getEmailProveedor() {
        return emailProveedor;
    }

    public void setEmailProveedor(String emailProveedor) {
        this.emailProveedor = emailProveedor;
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

    public List<DetalleCompraResponseDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCompraResponseDTO> detalles) {
        this.detalles = detalles;
    }

    // DTO para detalles de compra en respuesta
    public static class DetalleCompraResponseDTO {
        private Long id;
        private String producto;
        private Integer cantidad;
        private Double precioUnitario;
        private Double subtotal;

        public DetalleCompraResponseDTO() {
        }

        public DetalleCompraResponseDTO(Long id, String producto, Integer cantidad, Double precioUnitario, Double subtotal) {
            this.id = id;
            this.producto = producto;
            this.cantidad = cantidad;
            this.precioUnitario = precioUnitario;
            this.subtotal = subtotal;
        }

        // Getters y Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getProducto() {
            return producto;
        }

        public void setProducto(String producto) {
            this.producto = producto;
        }

        public Integer getCantidad() {
            return cantidad;
        }

        public void setCantidad(Integer cantidad) {
            this.cantidad = cantidad;
        }

        public Double getPrecioUnitario() {
            return precioUnitario;
        }

        public void setPrecioUnitario(Double precioUnitario) {
            this.precioUnitario = precioUnitario;
        }

        public Double getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(Double subtotal) {
            this.subtotal = subtotal;
        }
    }
}

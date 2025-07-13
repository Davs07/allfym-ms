package com.grupo.allfym.ms_compra.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class CompraRequestDTO {

    @NotNull(message = "El proveedor ID es obligatorio")
    private Long proveedorId;

    @Positive(message = "El monto total debe ser mayor a cero")
    private Double montoTotal;

    private String observaciones;

    private List<DetalleCompraDTO> detalles;

    public CompraRequestDTO() {
    }

    public CompraRequestDTO(Long proveedorId, Double montoTotal, String observaciones, List<DetalleCompraDTO> detalles) {
        this.proveedorId = proveedorId;
        this.montoTotal = montoTotal;
        this.observaciones = observaciones;
        this.detalles = detalles;
    }

    // Getters y Setters
    public Long getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Long proveedorId) {
        this.proveedorId = proveedorId;
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

    public List<DetalleCompraDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCompraDTO> detalles) {
        this.detalles = detalles;
    }

    // DTO interno para detalles de compra
    public static class DetalleCompraDTO {
        private String producto;
        private Integer cantidad;
        private Double precioUnitario;

        public DetalleCompraDTO() {
        }

        public DetalleCompraDTO(String producto, Integer cantidad, Double precioUnitario) {
            this.producto = producto;
            this.cantidad = cantidad;
            this.precioUnitario = precioUnitario;
        }

        // Getters y Setters
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
    }
}

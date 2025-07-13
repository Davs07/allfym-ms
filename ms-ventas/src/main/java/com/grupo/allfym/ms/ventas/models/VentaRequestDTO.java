package com.grupo.allfym.ms.ventas.models;

import com.grupo.allfym.ms.ventas.enums.MetodoPago;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public class VentaRequestDTO {

    @NotNull(message = "El cliente es obligatorio")
    private Long clienteId = 1L;

    @NotNull(message = "El método de pago es obligatorio")
    private MetodoPago metodoPago;

    private List<DetalleVentaDTO> detalles;

    public VentaRequestDTO() {
    }

    public VentaRequestDTO(Long clienteId, MetodoPago metodoPago, List<DetalleVentaDTO> detalles) {
        this.clienteId = clienteId;
        this.metodoPago = metodoPago;
        this.detalles = detalles;
    }

    // Getters y Setters
    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public List<DetalleVentaDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVentaDTO> detalles) {
        this.detalles = detalles;
    }

    // DTO interno para detalles
    public static class DetalleVentaDTO {
        @NotNull(message = "El producto es obligatorio")
        private String producto;

        @Positive(message = "La cantidad debe ser positiva")
        private Integer cantidad;

        @Positive(message = "El precio unitario debe ser positivo")
        private BigDecimal precioUnitario;

        public DetalleVentaDTO() {
        }

        public DetalleVentaDTO(String producto, Integer cantidad, BigDecimal precioUnitario) {
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

        public BigDecimal getPrecioUnitario() {
            return precioUnitario;
        }

        public void setPrecioUnitario(BigDecimal precioUnitario) {
            this.precioUnitario = precioUnitario;
        }
    }
}

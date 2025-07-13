package com.grupo.allfym.ms.ventas.models;

import com.grupo.allfym.ms.ventas.enums.EstadoVenta;
import com.grupo.allfym.ms.ventas.enums.MetodoPago;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class VentaResponseDTO {

    private Long id;
    private Long clienteId;
    private String clienteNombre;
    private LocalDateTime fechaRegistro;
    private MetodoPago metodoPago;
    private EstadoVenta estado;
    private BigDecimal total;
    private List<DetalleVentaResponseDTO> detalles;

    public VentaResponseDTO() {
    }

    public VentaResponseDTO(Long id, Long clienteId, String clienteNombre, LocalDateTime fechaRegistro,
                           MetodoPago metodoPago, EstadoVenta estado, BigDecimal total,
                           List<DetalleVentaResponseDTO> detalles) {
        this.id = id;
        this.clienteId = clienteId;
        this.clienteNombre = clienteNombre;
        this.fechaRegistro = fechaRegistro;
        this.metodoPago = metodoPago;
        this.estado = estado;
        this.total = total;
        this.detalles = detalles;
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

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
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

    public List<DetalleVentaResponseDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVentaResponseDTO> detalles) {
        this.detalles = detalles;
    }

    // DTO para respuesta de detalles
    public static class DetalleVentaResponseDTO {
        private Long id;
        private String producto;
        private Integer cantidad;
        private BigDecimal precioUnitario;
        private BigDecimal subtotal;

        public DetalleVentaResponseDTO() {
        }

        public DetalleVentaResponseDTO(Long id, String producto, Integer cantidad,
                                      BigDecimal precioUnitario, BigDecimal subtotal) {
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

        public BigDecimal getPrecioUnitario() {
            return precioUnitario;
        }

        public void setPrecioUnitario(BigDecimal precioUnitario) {
            this.precioUnitario = precioUnitario;
        }

        public BigDecimal getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(BigDecimal subtotal) {
            this.subtotal = subtotal;
        }
    }
}

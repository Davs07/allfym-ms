package com.grupo.allfym.ms.ventas.infrastructure.dtos;

import com.grupo.allfym.ms.ventas.domain.models.enums.EstadoVenta;
import com.grupo.allfym.ms.ventas.domain.models.enums.MetodoPago;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO para enviar responses de ventas desde la API REST.
 */
public class VentaResponseDto {

    private Long id;
    private Long clienteId;
    private LocalDateTime fechaRegistro;
    private MetodoPago metodoPago;
    private EstadoVenta estado;
    private BigDecimal total;
    private List<DetalleVentaResponseDto> detalles;

    // Constructores
    public VentaResponseDto() {
    }

    public VentaResponseDto(Long id, Long clienteId, LocalDateTime fechaRegistro, 
                           MetodoPago metodoPago, EstadoVenta estado, BigDecimal total) {
        this.id = id;
        this.clienteId = clienteId;
        this.fechaRegistro = fechaRegistro;
        this.metodoPago = metodoPago;
        this.estado = estado;
        this.total = total;
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

    public List<DetalleVentaResponseDto> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVentaResponseDto> detalles) {
        this.detalles = detalles;
    }

    /**
     * DTO para representar detalles de venta en responses.
     */
    public static class DetalleVentaResponseDto {
        
        private Long id;
        private String producto;
        private Integer cantidad;
        private BigDecimal precioUnitario;
        private BigDecimal subtotal;

        // Constructores
        public DetalleVentaResponseDto() {
        }

        public DetalleVentaResponseDto(Long id, String producto, Integer cantidad, 
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

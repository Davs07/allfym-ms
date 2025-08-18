package com.grupo.allfym.ms.ventas.infrastructure.dtos;

import com.grupo.allfym.ms.ventas.domain.models.enums.MetodoPago;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO para recibir requests de creación de ventas desde la API REST.
 */
public class VentaRequestDto {

    @NotNull(message = "El cliente es obligatorio")
    private Long clienteId;

    @NotNull(message = "El método de pago es obligatorio")
    private MetodoPago metodoPago;

    @NotEmpty(message = "Debe incluir al menos un detalle de venta")
    @Valid
    private List<DetalleVentaDto> detalles;

    // Constructores
    public VentaRequestDto() {
    }

    public VentaRequestDto(Long clienteId, MetodoPago metodoPago, List<DetalleVentaDto> detalles) {
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

    public List<DetalleVentaDto> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVentaDto> detalles) {
        this.detalles = detalles;
    }

    /**
     * DTO para representar detalles de venta en requests.
     */
    public static class DetalleVentaDto {
        
    @NotNull(message = "El ID del producto es obligatorio")
    private Long productoId;

        @Positive(message = "La cantidad debe ser positiva")
        private Integer cantidad;

        @Positive(message = "El precio unitario debe ser positivo")
        private BigDecimal precioUnitario;

        // Constructores
        public DetalleVentaDto() {
        }

        public DetalleVentaDto(Long productoId, Integer cantidad, BigDecimal precioUnitario) {
            this.productoId = productoId;
            this.cantidad = cantidad;
            this.precioUnitario = precioUnitario;
        }

        // Getters y Setters
        public Long getProductoId() {
            return productoId;
        }

        public void setProductoId(Long productoId) {
            this.productoId = productoId;
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

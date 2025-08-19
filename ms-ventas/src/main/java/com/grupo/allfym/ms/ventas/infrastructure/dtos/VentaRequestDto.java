package com.grupo.allfym.ms.ventas.infrastructure.dtos;

import com.grupo.allfym.ms.ventas.domain.models.entities.DetalleVenta;
import com.grupo.allfym.ms.ventas.domain.models.entities.Venta;
import com.grupo.allfym.ms.ventas.domain.models.enums.MetodoPago;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public class VentaRequestDto {

    @NotNull(message = "El cliente es obligatorio")
    private Long clienteId;

    @NotNull(message = "El método de pago es obligatorio")
    private MetodoPago metodoPago;

    @NotEmpty(message = "Debe incluir al menos un detalle de venta")
    @Valid
    private List<DetalleVentaDto> detalles;

    public VentaRequestDto() {
    }

    public VentaRequestDto(Long clienteId, MetodoPago metodoPago, List<DetalleVentaDto> detalles) {
        this.clienteId = clienteId;
        this.metodoPago = metodoPago;
        this.detalles = detalles;
    }

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

    // Conversión al modelo de dominio
    public static Venta toDomainModel(VentaRequestDto dto) {
        Venta venta = new Venta(
                dto.getClienteId(),
                dto.getMetodoPago()
        );
        if (dto.getDetalles() != null) {
            for (DetalleVentaDto d : dto.getDetalles()) {
                DetalleVenta detalle = new DetalleVenta(
                        d.getProductoId(),
                        d.getCantidad(),
                        d.getPrecioUnitario()
                );
                venta.agregarDetalle(detalle);
            }
        }
        return venta;
    }

 
    public static class DetalleVentaDto {
        
    @NotNull(message = "El ID del producto es obligatorio")
    private Long productoId;

        @Positive(message = "La cantidad debe ser positiva")
        private Integer cantidad;

        @Positive(message = "El precio unitario debe ser positivo")
        private BigDecimal precioUnitario;

        public DetalleVentaDto() {
        }

        public DetalleVentaDto(Long productoId, Integer cantidad, BigDecimal precioUnitario) {
            this.productoId = productoId;
            this.cantidad = cantidad;
            this.precioUnitario = precioUnitario;
        }

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

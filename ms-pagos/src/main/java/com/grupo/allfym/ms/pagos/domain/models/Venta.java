package com.grupo.allfym.ms.pagos.domain.models;
import java.math.BigDecimal;
import java.util.List;

public class Venta {
    private Long id;
    private Long clienteId;
    private String metodoPago;
    private BigDecimal total;
    private List<DetalleVenta> detalles;

    public Venta() {
    }

    public Venta(Long id, Long clienteId, String metodoPago, BigDecimal total, List<DetalleVenta> detalles) {
        this.id = id;
        this.clienteId = clienteId;
        this.metodoPago = metodoPago;
        this.total = total;
        this.detalles = detalles;
    }

    public Long getId() {
        return id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    // Clase interna
    public static class DetalleVenta {
        private String productoId;
        private Integer cantidad;
        private BigDecimal precioUnitario;

        public DetalleVenta() {
        }

        public DetalleVenta(String productoId, Integer cantidad, BigDecimal precioUnitario) {
            this.productoId = productoId;
            this.cantidad = cantidad;
            this.precioUnitario = precioUnitario;
        }

        public String getProductoId() {
            return productoId;
        }

        public Integer getCantidad() {
            return cantidad;
        }

        public BigDecimal getPrecioUnitario() {
            return precioUnitario;
        }
    }
}

package com.grupo.allfym.ms.pagos.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {
    private Long id;
    private Long clienteId;
    private String metodoPago;
    private BigDecimal total;
    private List<DetalleVenta> detalles;

    // Clase interna
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetalleVenta {
        private String productoId;
        private Integer cantidad;
        private BigDecimal precioUnitario;
    }
}

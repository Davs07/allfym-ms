package com.grupo.allfym.ms.ventas.models;

import com.grupo.allfym.ms.ventas.enums.EstadoVenta;
import com.grupo.allfym.ms.ventas.enums.MetodoPago;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaResponseDTO {

    private Long id;
    private Long clienteId;
    private String clienteNombre;
    private LocalDateTime fechaRegistro;
    private MetodoPago metodoPago;
    private EstadoVenta estado;
    private BigDecimal total;
    private List<DetalleVentaResponseDTO> detalles;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetalleVentaResponseDTO {
        private Long id;
        private Long productoId;
        private String productoNombre;
        private String productoDescripcion;
        private BigDecimal productoPrecio;
        private String productoCategoria;
        private Integer cantidad;
        private BigDecimal precioUnitario;
        private BigDecimal subtotal;
    }
}
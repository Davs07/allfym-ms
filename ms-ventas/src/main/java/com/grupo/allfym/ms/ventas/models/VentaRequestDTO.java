package com.grupo.allfym.ms.ventas.models;

import com.grupo.allfym.ms.ventas.enums.MetodoPago;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VentaRequestDTO {

    @NotNull(message = "El cliente es obligatorio")
    private Long clienteId;

    @NotNull(message = "El método de pago es obligatorio")
    private MetodoPago metodoPago;

    @NotEmpty(message = "Debe incluir al menos un detalle de venta")
    @Valid // Importante: valida cada elemento de la lista
    private List<DetalleVentaDTO> detalles;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetalleVentaDTO {
        @NotNull(message = "El producto es obligatorio")
        private Long productoId;

        @Positive(message = "La cantidad debe ser positiva")
        private Integer cantidad;

        @Positive(message = "El precio unitario debe ser positivo")
        private BigDecimal precioUnitario;

        // Método para calcular subtotal
        public BigDecimal calcularSubtotal() {
            if (cantidad != null && precioUnitario != null) {
                return precioUnitario.multiply(BigDecimal.valueOf(cantidad));
            }
            return BigDecimal.ZERO;
        }
    }
}

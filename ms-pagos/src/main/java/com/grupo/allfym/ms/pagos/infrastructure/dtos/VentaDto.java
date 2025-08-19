package com.grupo.allfym.ms.pagos.infrastructure.dtos;

import com.grupo.allfym.ms.pagos.domain.models.Venta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDto {
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

    public static VentaDto fromDomainModel(Venta venta) {
        List<DetalleVenta> detallesDto = venta.getDetalles()
                .stream()
                .map(d -> new DetalleVenta(
                        d.getProductoId(),
                        d.getCantidad(),
                        d.getPrecioUnitario()
                ))
                .collect(Collectors.toList());

        return new VentaDto(
                venta.getId(),
                venta.getClienteId(),
                venta.getMetodoPago(),
                venta.getTotal(),
                detallesDto
        );
    }

    public Venta toDomainModel() {
        return new Venta(
                getId(),
                getClienteId(),
                getMetodoPago(),
                getTotal(),
                getDetalles().stream()
                        .map(d -> new Venta.DetalleVenta(
                                d.getProductoId(),
                                d.getCantidad(),
                                d.getPrecioUnitario()
                        ))
                        .toList()
        );
    }
}

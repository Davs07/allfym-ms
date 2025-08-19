package com.grupo.allfym.ms.pagos.infrastructure.entities;

import com.grupo.allfym.ms.pagos.domain.models.classes.Pago;
import com.grupo.allfym.ms.pagos.domain.models.enums.EstadoPago;
import com.grupo.allfym.ms.pagos.infrastructure.dtos.VentaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Pago")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PagoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;
    private double monto;
    @Enumerated(EnumType.STRING)
    private EstadoPago estadoPago;

    //en la tabla Pago, la columna id_comprobante es una FK que apunta a ComprobantePago
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idComprobante")
    private ComprobantePagoEntity comprobantePagoEntity;

    //en la tabla Pago, la columna idVenta es una FK que apunta a Pago_venta
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_venta")
    private Pago_ventaEntity pagoVentaEntity;
    @Transient
    private VentaDto ventaDto;

    public static PagoEntity fromDomainModel(Pago pago) {
        return new PagoEntity(
                pago.getIdPago(),
                pago.getMonto(),
                pago.getEstadoPago(),
                pago.getComprobantePago() != null ? ComprobantePagoEntity.fromDomainModel(pago.getComprobantePago()) : null,
                pago.getPagoVenta() != null ? Pago_ventaEntity.fromDomainModel(pago.getPagoVenta()) : null,
                pago.getVenta() != null ? VentaDto.fromDomainModel(pago.getVenta()) : null
        );
    }

    public Pago toDomainModel() {
        return new Pago(
                idPago,
                monto,
                estadoPago,
                comprobantePagoEntity != null ? comprobantePagoEntity.toDomainModel() : null,
                pagoVentaEntity != null ? pagoVentaEntity.toDomainModel() : null,
                ventaDto != null ? ventaDto.toDomainModel() : null
        );
    }

}

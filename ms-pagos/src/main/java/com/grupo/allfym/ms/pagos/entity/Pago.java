package com.grupo.allfym.ms.pagos.entity;

import com.grupo.allfym.ms.pagos.enums.EstadoPago;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;

    @Positive(message = "El monto debe ser mayor a cero")
    private double monto;

    @Enumerated(EnumType.STRING)
    private EstadoPago estadoPago;

    @NotNull(message = "La venta ID es obligatoria")
    @Column(name = "venta_id", nullable = false)
    private Long ventaId;

    //en la tabla Pago, la columna id_comprobante es una FK que apunta a ComprobantePago
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idComprobante")
    private ComprobantePago comprobantePago;

}

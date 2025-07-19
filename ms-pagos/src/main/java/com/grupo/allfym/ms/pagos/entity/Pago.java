package com.grupo.allfym.ms.pagos.entity;

import com.grupo.allfym.ms.pagos.enums.EstadoPago;
import jakarta.persistence.*;
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
    private double monto;
    @Enumerated(EnumType.STRING)
    private EstadoPago estadoPago;

    //en la tabla Pago, la columna id_comprobante es una FK que apunta a ComprobantePago
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idComprobante")
    private ComprobantePago comprobantePago;

}

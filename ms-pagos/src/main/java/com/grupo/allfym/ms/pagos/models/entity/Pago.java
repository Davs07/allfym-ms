package com.grupo.allfym.ms.pagos.models.entity;

import com.grupo.allfym.ms.pagos.enums.EstadoPago;
import com.grupo.allfym.ms.pagos.models.Venta;
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

    //en la tabla Pago, la columna idVenta es una FK que apunta a Pago_venta
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_venta")
    private Pago_venta pagoVenta;
    @Transient
    private Venta venta;


}

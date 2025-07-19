package com.grupo.allfym.ms.pagos.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PagoVenta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago_venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_venta", unique = true)
    private Long idVenta;

    @Override
    public boolean equals(Object obj) {
        if (this==obj)
            return true;
        if (!(obj instanceof Pago))
            return false;

        Pago_venta rec = (Pago_venta) obj;
        return this.idVenta != null && this.idVenta.equals(rec.idVenta);
    }
}

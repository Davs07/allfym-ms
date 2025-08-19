package com.grupo.allfym.ms.pagos.domain.models.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Setter
public class Pago_venta {
    private Long id;
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

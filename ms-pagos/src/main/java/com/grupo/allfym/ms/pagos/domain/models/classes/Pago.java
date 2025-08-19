package com.grupo.allfym.ms.pagos.domain.models.classes;

import com.grupo.allfym.ms.pagos.domain.models.Venta;
import com.grupo.allfym.ms.pagos.domain.models.enums.EstadoPago;
import lombok.Data;

@Data
public class Pago {
    private Long idPago;
    private double monto;
    private EstadoPago estadoPago;
    private ComprobantePago comprobantePago;
    private Pago_venta pagoVenta;
    private Venta venta;

    public Pago(Long idPago, double monto, EstadoPago estadoPago, ComprobantePago comprobantePago, Pago_venta pagoVenta, Venta venta) {
        this.idPago = idPago;
        this.monto = monto;
        this.estadoPago = estadoPago;
        this.comprobantePago = comprobantePago;
        this.pagoVenta = pagoVenta;
        this.venta = venta;
    }
}

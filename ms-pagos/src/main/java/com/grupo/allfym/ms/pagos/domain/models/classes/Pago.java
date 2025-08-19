package com.grupo.allfym.ms.pagos.domain.models.classes;

import com.grupo.allfym.ms.pagos.domain.models.Venta;
import com.grupo.allfym.ms.pagos.domain.models.enums.EstadoPago;

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

    public Long getIdPago() {
        return idPago;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public EstadoPago getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(EstadoPago estadoPago) {
        this.estadoPago = estadoPago;
    }

    public ComprobantePago getComprobantePago() {
        return comprobantePago;
    }

    public void setComprobantePago(ComprobantePago comprobantePago) {
        this.comprobantePago = comprobantePago;
    }

    public Pago_venta getPagoVenta() {
        return pagoVenta;
    }

    public void setPagoVenta(Pago_venta pagoVenta) {
        this.pagoVenta = pagoVenta;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}

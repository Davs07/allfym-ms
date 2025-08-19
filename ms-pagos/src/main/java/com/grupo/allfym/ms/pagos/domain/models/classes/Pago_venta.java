package com.grupo.allfym.ms.pagos.domain.models.classes;

public class Pago_venta {
    private Long id;
    private Long idVenta;

    public Pago_venta(Long id, Long idVenta) {
        this.id = id;
        this.idVenta = idVenta;
    }

    public Pago_venta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

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

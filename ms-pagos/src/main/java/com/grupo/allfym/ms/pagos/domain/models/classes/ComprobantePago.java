package com.grupo.allfym.ms.pagos.domain.models.classes;

import com.grupo.allfym.ms.pagos.domain.models.enums.MetodoPago;
import com.grupo.allfym.ms.pagos.domain.models.enums.TipoComprobante;
import com.grupo.allfym.ms.pagos.domain.models.vo.FechaEmision;

public class ComprobantePago {
    private Long idComprobante;
    private boolean validadoSunat;
    private TipoComprobante tipoComprobante;
    private MetodoPago metodoPago;
    private FechaEmision fechaEmision;

    public ComprobantePago() {
    }

    public ComprobantePago(Long idComprobante, boolean validadoSunat, TipoComprobante tipoComprobante, MetodoPago metodoPago, FechaEmision fechaEmision) {
        this.idComprobante = idComprobante;
        this.validadoSunat = validadoSunat;
        this.tipoComprobante = tipoComprobante;
        this.metodoPago = metodoPago;
        this.fechaEmision = fechaEmision;
    }

    public Long getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(Long idComprobante) {
        this.idComprobante = idComprobante;
    }

    public boolean isValidadoSunat() {
        return validadoSunat;
    }

    public void setValidadoSunat(boolean validadoSunat) {
        this.validadoSunat = validadoSunat;
    }

    public TipoComprobante getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(TipoComprobante tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public FechaEmision getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(FechaEmision fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
}

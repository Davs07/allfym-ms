package com.grupo.allfym.ms.pagos.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PagoRequestDTO {

    @NotNull(message = "La venta ID es obligatoria")
    private Long ventaId;

    @Positive(message = "El monto debe ser mayor a cero")
    private Double monto;

    private String metodoPago;
    private String tipoComprobante;
    private String numeroComprobante;

    public PagoRequestDTO() {
    }

    public PagoRequestDTO(Long ventaId, Double monto, String metodoPago, String tipoComprobante, String numeroComprobante) {
        this.ventaId = ventaId;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.tipoComprobante = tipoComprobante;
        this.numeroComprobante = numeroComprobante;
    }

    // Getters y Setters
    public Long getVentaId() {
        return ventaId;
    }

    public void setVentaId(Long ventaId) {
        this.ventaId = ventaId;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }
}

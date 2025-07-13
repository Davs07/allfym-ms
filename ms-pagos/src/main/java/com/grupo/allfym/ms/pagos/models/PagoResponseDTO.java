package com.grupo.allfym.ms.pagos.models;

import java.time.LocalDateTime;

public class PagoResponseDTO {

    private Long idPago;
    private Long ventaId;
    private Double monto;
    private String estadoPago;
    private String numeroComprobante;
    private String tipoComprobante;
    private LocalDateTime fechaEmision;

    // Información de la venta (obtenida del microservicio de ventas)
    private String nombreCliente;
    private String estadoVenta;
    private Double totalVenta;

    public PagoResponseDTO() {
    }

    public PagoResponseDTO(Long idPago, Long ventaId, Double monto, String estadoPago,
                          String numeroComprobante, String tipoComprobante, LocalDateTime fechaEmision,
                          String nombreCliente, String estadoVenta, Double totalVenta) {
        this.idPago = idPago;
        this.ventaId = ventaId;
        this.monto = monto;
        this.estadoPago = estadoPago;
        this.numeroComprobante = numeroComprobante;
        this.tipoComprobante = tipoComprobante;
        this.fechaEmision = fechaEmision;
        this.nombreCliente = nombreCliente;
        this.estadoVenta = estadoVenta;
        this.totalVenta = totalVenta;
    }

    // Getters y Setters
    public Long getIdPago() {
        return idPago;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }

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

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(String estadoVenta) {
        this.estadoVenta = estadoVenta;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }
}

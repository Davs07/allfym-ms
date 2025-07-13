package com.grupo.allfym.ms.pagos.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VentaResponseDTO {

    private Long id;
    private Long clienteId;
    private String nombreCliente;
    private LocalDateTime fechaRegistro;
    private String metodoPago;
    private String estado;
    private BigDecimal total;

    public VentaResponseDTO() {
    }

    public VentaResponseDTO(Long id, Long clienteId, String nombreCliente, LocalDateTime fechaRegistro,
                           String metodoPago, String estado, BigDecimal total) {
        this.id = id;
        this.clienteId = clienteId;
        this.nombreCliente = nombreCliente;
        this.fechaRegistro = fechaRegistro;
        this.metodoPago = metodoPago;
        this.estado = estado;
        this.total = total;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}

package com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.models.enums;

public enum EstadoReclamo {
    PENDIENTE("Pendiente"),
    EN_INVESTIGACION("En investigación"),
    ACEPTADO("Aceptado"),
    RECHAZADO("Rechazado"),
    CERRADO("Cerrado");

    private final String descripcion;

    EstadoReclamo(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}

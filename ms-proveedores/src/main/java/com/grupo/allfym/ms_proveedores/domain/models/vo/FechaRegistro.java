package com.grupo.allfym.ms_proveedores.domain.models.vo;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@EqualsAndHashCode
@ToString
public final class FechaRegistro {
    private final LocalDate fecha;
    public FechaRegistro() {
        this.fecha = LocalDate.now();
    }

    public FechaRegistro(LocalDate fecha) {
        this.fecha = (fecha != null) ? fecha : LocalDate.now();
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
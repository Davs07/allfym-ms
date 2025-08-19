package com.grupo.allfym.ms_proveedores.infrastructure.entities;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
public class FechaDeRegistroEmbeddable {

    private LocalDate fecha;

    protected FechaDeRegistroEmbeddable() {}

    public FechaDeRegistroEmbeddable(LocalDate fecha) {
        this.fecha = (fecha != null) ? fecha : LocalDate.now();
    }

    public LocalDate getFecha() {
        return fecha;
    }

// getter y setter
}
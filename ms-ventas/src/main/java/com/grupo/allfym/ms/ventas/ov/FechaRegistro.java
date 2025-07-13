package com.grupo.allfym.ms.ventas.ov;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class FechaRegistro {

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    public FechaRegistro() {
        this.fechaRegistro = LocalDateTime.now();
    }

    public FechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FechaRegistro that = (FechaRegistro) o;
        return fechaRegistro != null ? fechaRegistro.equals(that.fechaRegistro) : that.fechaRegistro == null;
    }

    @Override
    public int hashCode() {
        return fechaRegistro != null ? fechaRegistro.hashCode() : 0;
    }
}

package com.grupo.allfym.ms.ventas.domain.models.vo;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Value Object que representa una fecha de registro en el dominio.
 * Inmutable y sin dependencias de frameworks externos.
 */
public class FechaRegistro {

    private final LocalDateTime fechaRegistro;

    public FechaRegistro() {
        this.fechaRegistro = LocalDateTime.now();
    }

    public FechaRegistro(LocalDateTime fechaRegistro) {
        if (fechaRegistro == null) {
            throw new IllegalArgumentException("La fecha de registro no puede ser nula");
        }
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public boolean esAnteriorA(FechaRegistro otra) {
        return this.fechaRegistro.isBefore(otra.fechaRegistro);
    }

    public boolean esPosteriorA(FechaRegistro otra) {
        return this.fechaRegistro.isAfter(otra.fechaRegistro);
    }

    public boolean esHoy() {
        LocalDateTime hoy = LocalDateTime.now();
        return fechaRegistro.toLocalDate().equals(hoy.toLocalDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FechaRegistro that = (FechaRegistro) o;
        return Objects.equals(fechaRegistro, that.fechaRegistro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaRegistro);
    }

    @Override
    public String toString() {
        return fechaRegistro.toString();
    }
}

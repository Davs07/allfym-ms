package com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.models.valueObjects;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FechaCreacion {
    private LocalDate fecha;

    public FechaCreacion() {
        this.fecha = LocalDate.now();
    }

    public FechaCreacion(LocalDate fecha) {
        if (fecha == null) {
            this.fecha = LocalDate.now();
        } else {
            this.fecha = fecha;
        }
    }

    public boolean esHoy() {
        return this.fecha.equals(LocalDate.now());
    }

    public boolean esPosteriorA(FechaCreacion otraFecha) {
        return this.fecha.isAfter(otraFecha.getFecha());
    }

    public boolean esAnteriorA(FechaCreacion otraFecha) {
        return this.fecha.isBefore(otraFecha.getFecha());
    }
}

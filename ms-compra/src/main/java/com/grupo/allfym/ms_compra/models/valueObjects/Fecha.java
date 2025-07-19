package com.grupo.allfym.ms_compra.models.valueObjects;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Embeddable
public class Fecha {
    private LocalDate fecha;

    public Fecha() {
        this.fecha = LocalDate.now();
    }

    public Fecha(LocalDate fecha) {
        if (fecha == null) {
            this.fecha = LocalDate.now();
        } else {
            this.fecha = fecha;
        }
    }
}

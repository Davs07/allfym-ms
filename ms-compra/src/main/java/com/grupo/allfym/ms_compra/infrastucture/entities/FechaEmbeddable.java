package com.grupo.allfym.ms_compra.infrastucture.entities;


import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@Data
@NoArgsConstructor

public class FechaEmbeddable {
    private LocalDate fecha;

    public FechaEmbeddable(LocalDate fecha) {
        this.fecha = (fecha != null) ? fecha : LocalDate.now();
    }
}

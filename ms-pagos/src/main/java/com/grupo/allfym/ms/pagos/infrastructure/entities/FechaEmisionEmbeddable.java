package com.grupo.allfym.ms.pagos.infrastructure.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Embeddable
@Data
@AllArgsConstructor
public class FechaEmisionEmbeddable {
    private int dia;
    private int mes;
    private int anio;

    public FechaEmisionEmbeddable() {
        this.dia = LocalDateTime.now().getDayOfMonth();
        this.mes = LocalDateTime.now().getMonthValue();
        this.anio = LocalDateTime.now().getYear();
    }

    public FechaEmisionEmbeddable(LocalDateTime fecha) {
        this.dia = fecha.getDayOfMonth();
        this.mes = fecha.getMonthValue();
        this.anio = fecha.getYear();
    }
}

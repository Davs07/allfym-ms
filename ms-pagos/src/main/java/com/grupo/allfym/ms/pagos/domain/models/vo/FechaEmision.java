package com.grupo.allfym.ms.pagos.domain.models.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class FechaEmision {
    private int dia;
    private int mes;
    private int anio;

    public FechaEmision() {
        this.dia = LocalDateTime.now().getDayOfMonth();
        this.mes = LocalDateTime.now().getMonthValue();
        this.anio = LocalDateTime.now().getYear();
    }

    public FechaEmision(LocalDateTime fecha) {
        this.dia = fecha.getDayOfMonth();
        this.mes = fecha.getMonthValue();
        this.anio = fecha.getYear();
    }
}

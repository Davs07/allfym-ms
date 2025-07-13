package com.grupo.allfym.ms.pagos.ov;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@Data
@AllArgsConstructor
public class FechaEmision {
    private int dia;
    private int mes;
    private int año;

    public FechaEmision() {
        this.dia = LocalDateTime.now().getDayOfMonth();
        this.mes = LocalDateTime.now().getMonthValue();
        this.año = LocalDateTime.now().getYear();
    }

    public FechaEmision(LocalDateTime fecha) {
        this.dia = fecha.getDayOfMonth();
        this.mes = fecha.getMonthValue();
        this.año = fecha.getYear();
    }
}

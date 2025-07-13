package com.grupo.allfym.ms.reclamos.vo;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@Data
@AllArgsConstructor
public class fechaReclamo {
    private int dia;
    private int mes;
    private int año;

    public fechaReclamo() {
        this.dia = LocalDateTime.now().getDayOfMonth();
        this.mes = LocalDateTime.now().getMonthValue();
        this.año = LocalDateTime.now().getYear();
    }

    public fechaReclamo(LocalDateTime fecha) {
        this.dia = fecha.getDayOfMonth();
        this.mes = fecha.getMonthValue();
        this.año = fecha.getYear();
    }

}

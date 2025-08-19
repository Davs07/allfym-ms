package com.grupo.allfym.ms.reclamos.domain.models.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class FechaReclamo {
    private final LocalDateTime fecha;

    public FechaReclamo() {
        this.fecha = LocalDateTime.now();
    }
}

package com.grupo.allfym.ms.reclamos.domain.models.vo;

import java.time.LocalDateTime;

public class FechaReclamo {
    private final LocalDateTime fecha;

    public FechaReclamo() {
        this.fecha = LocalDateTime.now();
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public FechaReclamo(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}

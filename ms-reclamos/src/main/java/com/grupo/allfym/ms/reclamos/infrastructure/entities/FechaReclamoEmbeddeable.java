package com.grupo.allfym.ms.reclamos.infrastructure.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Embeddable
public class FechaReclamoEmbeddeable {
    private final LocalDateTime fecha;

    public FechaReclamoEmbeddeable() {
        this.fecha = LocalDateTime.now();
    }
}

package com.grupo.allfym.ms_compra.domain.models.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.time.LocalDate;

public class Fecha {
    private final LocalDate fecha;

    public Fecha() {
        this.fecha = LocalDate.now();
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)

    public Fecha(LocalDate fecha) {
        this.fecha = (fecha != null) ? fecha : LocalDate.now();
    }

    @JsonValue
    public LocalDate getFecha() {
        return fecha;
    }
}

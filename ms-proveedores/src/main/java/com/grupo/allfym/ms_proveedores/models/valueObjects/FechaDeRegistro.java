package com.grupo.allfym.ms_proveedores.models.valueObjects;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Embeddable
@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class FechaDeRegistro {
    private LocalDate fecha;
    public FechaDeRegistro() {
        this.fecha = LocalDate.now();
    }

    public FechaDeRegistro(LocalDate fecha) {
        if (fecha == null) {
            this.fecha = LocalDate.now();
        } else {
            this.fecha = fecha;
        }
    }
}

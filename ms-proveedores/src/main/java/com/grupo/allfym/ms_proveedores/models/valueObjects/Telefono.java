package com.grupo.allfym.ms_proveedores.models.valueObjects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class Telefono {
    private String numero;

    public Telefono(String numero) {
        if (numero == null || numero.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de teléfono no puede estar vacio");
        }
        if (!numero.matches("\\d{9}")) {
            throw new IllegalArgumentException("El número de teléfono debe tener 9 digitos");
        }
        this.numero = numero;
    }
}

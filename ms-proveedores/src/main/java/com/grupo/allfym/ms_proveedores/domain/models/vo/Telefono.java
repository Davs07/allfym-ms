package com.grupo.allfym.ms_proveedores.domain.models.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public final class Telefono {
    private final String numero;

    public Telefono(String numero) {
        if (numero == null || numero.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de teléfono no puede estar vacío");
        }
        if (!numero.matches("\\d{9}")) {
            throw new IllegalArgumentException("El número de teléfono debe tener 9 dígitos");
        }
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }
}

package com.grupo.allfym.ms_proveedores.domain.models.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public final class Email {
    private final String email;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public Email(String em) {
        if (em == null || em.trim().isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío");
        }
        if (!em.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("El formato del email no es válido");
        }
        this.email = em;
    }

    @JsonValue
    public String getEmail() {
        return this.email;
    }

}

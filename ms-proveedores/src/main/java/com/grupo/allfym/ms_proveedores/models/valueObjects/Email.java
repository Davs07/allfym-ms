package com.grupo.allfym.ms_proveedores.models.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class Email {
    private String email;

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
//    public Email(String em) {
//        if (em == null || em.trim().isEmpty()) {
//            throw new IllegalArgumentException("El email no puede estar vacío");
//        }
//        if (!em.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
//            throw new IllegalArgumentException("El formato del email no es valido");
//        }
//        this.email = em;
//    }
}

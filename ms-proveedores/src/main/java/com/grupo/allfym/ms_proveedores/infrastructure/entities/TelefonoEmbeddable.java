package com.grupo.allfym.ms_proveedores.infrastructure.entities;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class TelefonoEmbeddable {

    private String numero;

    public TelefonoEmbeddable(String numero) {
        this.numero = numero;
    }

}
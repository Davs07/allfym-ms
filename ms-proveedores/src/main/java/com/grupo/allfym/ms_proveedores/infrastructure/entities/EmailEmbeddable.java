package com.grupo.allfym.ms_proveedores.infrastructure.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class EmailEmbeddable {
    private String email;

    protected EmailEmbeddable() {}

    public EmailEmbeddable(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}

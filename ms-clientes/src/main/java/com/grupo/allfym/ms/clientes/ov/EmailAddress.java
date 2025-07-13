package com.grupo.allfym.ms.clientes.ov;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class EmailAddress {

    @Email(message = "El formato del email no es válido")
    @NotBlank(message = "El email no puede estar vacío")
    @Column(name = "email")
    private String valor;

    public EmailAddress() {
    }

    public EmailAddress(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailAddress email = (EmailAddress) o;
        return valor != null ? valor.equals(email.valor) : email.valor == null;
    }

    @Override
    public int hashCode() {
        return valor != null ? valor.hashCode() : 0;
    }
}

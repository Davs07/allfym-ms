package com.grupo.allfym.ms.clientes.ov;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Pattern;

@Embeddable
public class Telefono {

    @Pattern(regexp = "^[+]?[0-9]{8,15}$", message = "El teléfono debe tener entre 8 y 15 dígitos")
    @Column(name = "telefono")
    private String numero;

    public Telefono() {
    }

    public Telefono(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefono telefono = (Telefono) o;
        return numero != null ? numero.equals(telefono.numero) : telefono.numero == null;
    }

    @Override
    public int hashCode() {
        return numero != null ? numero.hashCode() : 0;
    }
}

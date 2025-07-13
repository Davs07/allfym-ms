package com.grupo.allfym.ms_proveedores.models.valueObjects;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class Direccion {
    private String calle;
    private String ciudad;
    private String codigoPostal;
    private String pais;

    public Direccion(String calle, String ciudad, String codigoPostal, String pais) {
        if (calle == null || calle.trim().isEmpty()) {
            throw new IllegalArgumentException("La calle no puede estar vacía");
        }
        this.calle = calle;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.pais = pais;
    }
}

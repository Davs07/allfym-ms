package com.grupo.allfym.ms_proveedores.infrastructure.entities;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
public class DireccionEmbeddable {

    private String calle;
    private String ciudad;
    private String codigoPostal;
    private String pais;

    public DireccionEmbeddable(String calle, String ciudad, String codigoPostal, String pais) {
        this.calle = calle;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.pais = pais;
    }

}
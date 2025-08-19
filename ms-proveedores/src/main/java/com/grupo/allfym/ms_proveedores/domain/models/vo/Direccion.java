package com.grupo.allfym.ms_proveedores.domain.models.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public final class Direccion {

    private final String calle;
    private final String ciudad;
    private final String codigoPostal;
    private final String pais;

    @JsonCreator//deserializar correct
    public Direccion(
            @JsonProperty("calle") String calle,
            @JsonProperty("ciudad") String ciudad,
            @JsonProperty("codigoPostal") String codigoPostal,
            @JsonProperty("pais") String pais) {

        if (calle == null || calle.trim().isEmpty()) {
            throw new IllegalArgumentException("La calle no puede estar vacía");
        }
        if (ciudad == null || ciudad.trim().isEmpty()) {
            throw new IllegalArgumentException("La ciudad no puede estar vacía");
        }
        if (codigoPostal == null || codigoPostal.trim().isEmpty()) {
            throw new IllegalArgumentException("El código postal no puede estar vacío");
        }
        if (pais == null || pais.trim().isEmpty()) {
            throw new IllegalArgumentException("El país no puede estar vacío");
        }

        this.calle = calle;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.pais = pais;
    }
}
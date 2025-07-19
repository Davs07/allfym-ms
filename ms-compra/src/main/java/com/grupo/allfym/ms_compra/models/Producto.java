package com.grupo.allfym.ms_compra.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Producto {
    @JsonProperty("idProducto")
    private Long id;
    private String nombre;
}

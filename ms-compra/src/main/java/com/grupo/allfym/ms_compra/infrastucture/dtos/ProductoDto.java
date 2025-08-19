package com.grupo.allfym.ms_compra.infrastucture.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductoDto {
    @JsonProperty("idProducto")
    private Long id;
    private String nombre;
}

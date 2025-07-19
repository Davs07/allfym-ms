package com.grupo.allfym.ms.ventas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponseDTO {
    private Long idProducto;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String categoria;
    private String marca;
}

package com.grupo.allfym.ms.almacen.infrastructure.entities.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Producto {
    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private Categoria categoria;
    private Marca marca;
}
package com.grupo.allfym.ms.almacen.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//POJO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    private Long IdProducto;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Categoria categoria;
    private Marca marca;
}

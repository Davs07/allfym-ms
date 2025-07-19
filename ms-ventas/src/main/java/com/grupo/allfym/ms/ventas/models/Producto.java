package com.grupo.allfym.ms.ventas.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    private Long IdProducto;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String categoria;
    private String marca;
}
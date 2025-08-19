package com.grupo.allfym.ms.productos.domain.models;

import com.grupo.allfym.ms.productos.infrastructure.entities.Categoria;
import com.grupo.allfym.ms.productos.infrastructure.entities.Marca;
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
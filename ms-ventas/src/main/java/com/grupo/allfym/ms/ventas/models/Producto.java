package com.grupo.allfym.ms.ventas.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long IdProducto;
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;
    @Column(name = "precio", nullable = false)
    private Double precio;

    private String categoria;
    private String marca;
}

package com.grupo.allfym.ms.almacen.models.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "almacen_productos")
public class AlmacenProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_almacen")
    private Long idAlmacen;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "id_producto", nullable = false, unique = true)
    private Long idProducto;

    @OneToMany(mappedBy = "almacenProducto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Movimiento> movimientos = new ArrayList<>();
}
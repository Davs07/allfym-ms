package com.grupo.allfym.ms_compra.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalle_compra")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleCom;

    @ManyToOne
    @JoinColumn(name = "id_compra")
    @JsonBackReference
    private Compra compra;

    private Long idProducto;

    private Integer cantidad;

    private Double precioCompra;

    private Double subtotal;

}

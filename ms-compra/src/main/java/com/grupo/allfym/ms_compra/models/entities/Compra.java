package com.grupo.allfym.ms_compra.models.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.grupo.allfym.ms_compra.models.Proveedor;
import com.grupo.allfym.ms_compra.models.valueObjects.Fecha;
import com.grupo.allfym.ms_compra.models.enums.Estado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "compras")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Embedded
    @AttributeOverride(name = "fecha", column = @Column(name = "fecha_emision"))
    private Fecha fechaEmision;

    @Embedded
    @AttributeOverride(name = "fecha", column = @Column(name = "fecha_recepcion"))
    private Fecha fechaRecepcion;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(name = "id_proveedor", nullable = false)
    private Long idProveedor;

    private Double montoTotal;

    private String observaciones;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetalleCompra> detalles;
}

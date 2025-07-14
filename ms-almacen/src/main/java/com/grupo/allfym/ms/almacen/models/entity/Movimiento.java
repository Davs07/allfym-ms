package com.grupo.allfym.ms.almacen.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movimientos")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private Long idMovimiento;

    @Embedded
    private FechaMovimiento fechaMovimiento;

    @Column(name = "tipo_movimiento", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipoMovimiento;

    @Column(name = "cantidad_movimientos", nullable = false)
    private Integer cantidadDeMovimientos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_almacen", nullable = false)
    @JsonIgnore  // ← Evita la serialización de la referencia circular
    @JsonBackReference
    private AlmacenProducto almacenProducto;
}
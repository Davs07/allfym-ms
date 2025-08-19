package com.grupo.allfym.ms.almacen.infrastructure.entities;

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
public class MovimientoEntity {

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
    private AlmacenProductoEntity almacenProducto;

    public static MovimientoEntity fromDomainModel(com.grupo.allfym.ms.almacen.domain.models.Movimiento movimiento) {
        MovimientoEntity entity = new MovimientoEntity();
        entity.setIdMovimiento(movimiento.getIdMovimiento());
        entity.setTipoMovimiento(movimiento.getTipoMovimiento());
        entity.setCantidadDeMovimientos(movimiento.getCantidadDeMovimientos());
        entity.setFechaMovimiento(movimiento.getFechaMovimiento());
        return entity;
    }

    public com.grupo.allfym.ms.almacen.domain.models.Movimiento toDomainModel() {

        com.grupo.allfym.ms.almacen.domain.models.Movimiento movimiento =
                new com.grupo.allfym.ms.almacen.domain.models.Movimiento();
        movimiento.setIdMovimiento(idMovimiento);
        movimiento.setFechaMovimiento(fechaMovimiento);
        movimiento.setTipoMovimiento(tipoMovimiento);
        movimiento.setCantidadDeMovimientos(cantidadDeMovimientos);
        return movimiento;
    }
}
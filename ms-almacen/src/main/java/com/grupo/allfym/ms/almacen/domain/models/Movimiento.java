package com.grupo.allfym.ms.almacen.domain.models;

import com.grupo.allfym.ms.almacen.infrastructure.entities.FechaMovimiento;
import com.grupo.allfym.ms.almacen.infrastructure.entities.TipoMovimiento;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movimiento {
    private Long idMovimiento;
    private FechaMovimiento fechaMovimiento;
    private TipoMovimiento tipoMovimiento;
    private Integer cantidadDeMovimientos;

    @JsonBackReference
    private AlmacenProducto almacenProducto;
}
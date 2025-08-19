package com.grupo.allfym.ms.almacen.domain.models;

import com.grupo.allfym.ms.almacen.domain.models.Movimiento;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class AlmacenProducto {
    private Long idAlmacen;
    private Integer stock;
    private Long idProducto;

    @JsonManagedReference
    private List<Movimiento> movimientos;

    public AlmacenProducto() {
        this.movimientos = new ArrayList<>();
    }

    // Getter con verificación adicional
    public List<Movimiento> getMovimientos() {
        if (this.movimientos == null) {
            this.movimientos = new ArrayList<>();
        }
        return this.movimientos;
    }
}
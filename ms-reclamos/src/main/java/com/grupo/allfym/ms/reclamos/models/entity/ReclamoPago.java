package com.grupo.allfym.ms.reclamos.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Reclamo_pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReclamoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "idCliente", unique = true)
    private Long idCliente;

    @Override
    public boolean equals(Object obj) {
        if (this==obj)
            return true;
        if (!(obj instanceof ReclamoPago))
            return false;

        ReclamoPago rec = (ReclamoPago) obj;
        return this.idCliente != null && this.idCliente.equals(rec.idCliente);
    }

}

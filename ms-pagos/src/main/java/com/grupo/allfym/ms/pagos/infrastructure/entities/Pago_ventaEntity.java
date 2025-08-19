package com.grupo.allfym.ms.pagos.infrastructure.entities;

import com.grupo.allfym.ms.pagos.domain.models.classes.Pago_venta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PagoVenta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago_ventaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_venta", unique = true)
    private Long idVenta;

    public static Pago_ventaEntity fromDomainModel(Pago_venta pagoVenta) {
        return new Pago_ventaEntity(pagoVenta.getId(),pagoVenta.getIdVenta());
    }

    public Pago_venta toDomainModel (){
        return new Pago_venta(id,idVenta);
    }
}

package com.grupo.allfym.ms.pagos.models.entity;

import com.grupo.allfym.ms.pagos.enums.MetodoPago;
import com.grupo.allfym.ms.pagos.enums.TipoComprobante;
import com.grupo.allfym.ms.pagos.ov.FechaEmision;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ComprobantePago")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComprobantePago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComprobante;
    private boolean validadoSunat;
    @Enumerated(EnumType.STRING)
    private TipoComprobante tipoComprobante;
    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;
    @Embedded
    private FechaEmision fechaEmision;
}

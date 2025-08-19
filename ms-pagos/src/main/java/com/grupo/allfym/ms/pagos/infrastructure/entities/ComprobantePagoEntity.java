package com.grupo.allfym.ms.pagos.infrastructure.entities;

import com.grupo.allfym.ms.pagos.domain.models.classes.ComprobantePago;
import com.grupo.allfym.ms.pagos.domain.models.enums.MetodoPago;
import com.grupo.allfym.ms.pagos.domain.models.enums.TipoComprobante;
import com.grupo.allfym.ms.pagos.domain.models.vo.FechaEmision;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ComprobantePago")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComprobantePagoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComprobante;
    private boolean validadoSunat;
    @Enumerated(EnumType.STRING)
    private TipoComprobante tipoComprobante;
    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;
    @Embedded
    private FechaEmisionEmbeddable fechaEmision;

    public static ComprobantePagoEntity fromDomainModel (ComprobantePago comprobantePago) {
        return new ComprobantePagoEntity(comprobantePago.getIdComprobante(),comprobantePago.isValidadoSunat(),comprobantePago.getTipoComprobante(),comprobantePago.getMetodoPago(), new FechaEmisionEmbeddable());
    }

    public ComprobantePago toDomainModel() {
        return new ComprobantePago(idComprobante, validadoSunat,tipoComprobante,metodoPago,
                new FechaEmision(fechaEmision.getDia(), fechaEmision.getMes(), fechaEmision.getAnio()));
    }

}

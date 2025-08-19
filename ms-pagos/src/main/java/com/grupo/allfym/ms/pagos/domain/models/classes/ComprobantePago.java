package com.grupo.allfym.ms.pagos.domain.models.classes;

import com.grupo.allfym.ms.pagos.domain.models.enums.MetodoPago;
import com.grupo.allfym.ms.pagos.domain.models.enums.TipoComprobante;
import com.grupo.allfym.ms.pagos.domain.models.vo.FechaEmision;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComprobantePago {
    private Long idComprobante;
    private boolean validadoSunat;
    private TipoComprobante tipoComprobante;
    private MetodoPago metodoPago;
    private FechaEmision fechaEmision;
}

package com.grupo.allfym.ms.reclamos.models;

import lombok.Data;

@Data
public class ComprobantePago {
    private String tipoComprobante;
    private String metodoPago;
    private FechaEmision fechaEmision;
}

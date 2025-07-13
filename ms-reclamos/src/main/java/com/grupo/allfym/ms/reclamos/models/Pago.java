package com.grupo.allfym.ms.reclamos.models;

import lombok.Data;

//Clase pojo para consumir los pagos
@Data
public class Pago {
    private Long idPago;
    private double monto;
    private String estadoPago;
    private ComprobantePago comprobantePago;
}

package com.grupo.allfym.ms.pagos.enums;

public enum EstadoPago {
    PAGADO, POR_PAGAR;

    public static boolean valido(String estado) {
        try {
            EstadoPago.valueOf(estado);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

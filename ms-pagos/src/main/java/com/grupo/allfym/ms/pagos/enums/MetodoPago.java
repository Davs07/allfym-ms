package com.grupo.allfym.ms.pagos.enums;

public enum MetodoPago {
    EFECTIVO,
    TARJETA_CREDITO,
    TARJETA_DEBITO,
    TRANSFERENCIA,
    YAPE,
    PLIN;

    public static boolean valido(String metodo) {
        try {
            MetodoPago.valueOf(metodo);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

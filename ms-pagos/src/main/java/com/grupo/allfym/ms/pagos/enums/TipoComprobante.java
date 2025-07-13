package com.grupo.allfym.ms.pagos.enums;

public enum TipoComprobante {
    BOLETA,
    FACTURA,
    NOTA_CREDITO,
    NOTA_DEBITO;

    public static boolean valido(String tipo) {
        try {
            TipoComprobante.valueOf(tipo);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

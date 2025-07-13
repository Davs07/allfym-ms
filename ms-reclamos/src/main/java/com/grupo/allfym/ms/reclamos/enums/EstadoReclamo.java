package com.grupo.allfym.ms.reclamos.enums;

public enum EstadoReclamo {
    PENDIENTE, EN_PROCESO, ATENDIDO;

    public static boolean esValido(String estado) {
        try {
            EstadoReclamo.valueOf(estado);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

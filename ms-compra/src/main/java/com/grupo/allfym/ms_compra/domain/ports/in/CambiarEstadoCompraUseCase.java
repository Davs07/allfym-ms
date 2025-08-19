package com.grupo.allfym.ms_compra.domain.ports.in;

public interface CambiarEstadoCompraUseCase {
    void cambiarEstado(Long id, String estado);
}

package com.grupo.allfym.ms.pagos.services;

import com.grupo.allfym.ms.pagos.entity.Pago;

import java.util.List;
import java.util.Optional;

public interface PagoService {
    List<Pago> lista();
    Optional<Pago> porId(Long id);
    Pago guardar(Pago pago);
    void eliminar(Long id);
}

package com.grupo.allfym.ms.pagos.services;

import com.grupo.allfym.ms.pagos.models.Venta;
import com.grupo.allfym.ms.pagos.models.entity.Pago;

import java.util.List;
import java.util.Optional;

public interface PagoService {
    List<Pago> lista();
    Optional<Pago> porId(Long id);
    Pago guardar(Pago pago);
    void eliminar(Long id);

    //MetodosRemotos
    Optional<Venta> asignarVenta(Venta venta, Long id);
    Optional<Venta> removerVenta(Venta venta, Long id);
    List<Venta> listaVenta();

}

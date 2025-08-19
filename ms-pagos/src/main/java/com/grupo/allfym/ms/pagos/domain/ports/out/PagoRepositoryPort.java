package com.grupo.allfym.ms.pagos.domain.ports.out;

import com.grupo.allfym.ms.pagos.domain.models.Venta;
import com.grupo.allfym.ms.pagos.domain.models.classes.Pago;

import java.util.List;
import java.util.Optional;

public interface PagoRepositoryPort {
    List<Pago> lista();
    Optional<Pago> porId(Long id);
    Pago guardar(Pago pago);
    void eliminar(Long id);
    Optional<Pago> actualizar(Pago pago, Long id);
    List<Pago> listaMetodoPago(String metodo);

    Optional<Venta> asignarVenta(Venta venta, Long id);
    Optional<Venta> removerVenta(Venta venta, Long id);
}

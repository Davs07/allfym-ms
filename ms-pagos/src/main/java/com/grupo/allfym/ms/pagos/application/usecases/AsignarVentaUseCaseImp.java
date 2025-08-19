package com.grupo.allfym.ms.pagos.application.usecases;

import com.grupo.allfym.ms.pagos.domain.models.Venta;
import com.grupo.allfym.ms.pagos.domain.models.classes.Pago;
import com.grupo.allfym.ms.pagos.domain.models.classes.Pago_venta;
import com.grupo.allfym.ms.pagos.domain.ports.in.AsignarVentaUseCase;
import com.grupo.allfym.ms.pagos.domain.ports.out.PagoRepositoryPort;
import com.grupo.allfym.ms.pagos.domain.ports.out.VentaServicePort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class AsignarVentaUseCaseImp implements AsignarVentaUseCase {
    private final PagoRepositoryPort pagoRepositoryPort;
    private final VentaServicePort ventaServicePort;

    @Override
    public Optional<Venta> asignarVenta(Venta venta, Long id) {
        Optional<Pago> op = pagoRepositoryPort.porId(id);
        if (op.isPresent()) {
            Venta ventaMS = ventaServicePort.detalleVenta(venta.getId());
            //Asigno la relacion a Pago
            Pago pagoBD = op.get();
            Pago_venta pagoVenta = new Pago_venta();
            pagoVenta.setIdVenta(ventaMS.getId());
            //Lo guardo en la Base de datos
            pagoBD.setPagoVenta(pagoVenta);
            pagoRepositoryPort.guardar(pagoBD);
            return Optional.of(ventaMS);
        }
        return Optional.empty();
    }
}

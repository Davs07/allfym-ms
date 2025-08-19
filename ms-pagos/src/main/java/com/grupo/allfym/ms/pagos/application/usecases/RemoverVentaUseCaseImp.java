package com.grupo.allfym.ms.pagos.application.usecases;

import com.grupo.allfym.ms.pagos.domain.models.Venta;
import com.grupo.allfym.ms.pagos.domain.models.classes.Pago;
import com.grupo.allfym.ms.pagos.domain.ports.in.RemoverVentaUseCase;
import com.grupo.allfym.ms.pagos.domain.ports.out.PagoRepositoryPort;
import com.grupo.allfym.ms.pagos.domain.ports.out.VentaServicePort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class RemoverVentaUseCaseImp implements RemoverVentaUseCase {
    private final PagoRepositoryPort pagoRepositoryPort;
    private final VentaServicePort ventaServicePort;

    @Override
    public Optional<Venta> removerVenta(Venta venta, Long id) {
        Optional<Pago> op = pagoRepositoryPort.porId(id);
        if (op.isPresent()) {
            Venta ventaMS = ventaServicePort.detalleVenta(venta.getId());
            //Elimino la relacion con la venta
            Pago pagoBD = op.get();
            if (pagoBD.getPagoVenta() != null &&
                    pagoBD.getPagoVenta().getIdVenta().equals(venta.getId())) {

                // Rompo la relación
                pagoBD.setPagoVenta(null);
                pagoRepositoryPort.guardar(pagoBD);
                return Optional.of(venta);
            }
        }
        return Optional.empty();
    }
}

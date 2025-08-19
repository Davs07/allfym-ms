package com.grupo.allfym.ms.pagos.application.usecases;

import com.grupo.allfym.ms.pagos.domain.models.Venta;
import com.grupo.allfym.ms.pagos.domain.models.classes.Pago;
import com.grupo.allfym.ms.pagos.domain.ports.in.ObtenerPagoUseCase;
import com.grupo.allfym.ms.pagos.domain.ports.out.PagoRepositoryPort;
import com.grupo.allfym.ms.pagos.domain.ports.out.VentaServicePort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ObtenerPagoUseCaseImp implements ObtenerPagoUseCase {

    private final PagoRepositoryPort pagoRepositoryPort;
    private final VentaServicePort ventaServicePort;

    @Override
    public Optional<Pago> obtenerPago(Long id) {
        Optional<Pago> op = pagoRepositoryPort.porId(id);
        if (op.isPresent()) {
            Pago pago = op.get();
            if (pago.getPagoVenta() != null)
                pago.setVenta(ventaServicePort.detalleVenta(pago.getPagoVenta().getIdVenta()));
            return Optional.of(pago);
        }
        return Optional.empty() ;
    }

    @Override
    public List<Pago> listaPago() {
        List<Pago> lista_pagos = pagoRepositoryPort.lista();
        List<Venta> lista_venta = ventaServicePort.listaVenta();

        for (Pago pago : lista_pagos) {
            if (pago.getPagoVenta() != null) {
                for (Venta venta : lista_venta) {
                    if (pago.getPagoVenta().getIdVenta().equals(venta.getId())) {
                        pago.setVenta(venta);
                        pago.setMonto(venta.getTotal().doubleValue());
                        break;
                    }
                }
            }
        }
        return lista_pagos;
    }
}

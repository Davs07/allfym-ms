package com.grupo.allfym.ms.pagos.application.services;

import com.grupo.allfym.ms.pagos.domain.models.Venta;
import com.grupo.allfym.ms.pagos.domain.models.classes.Pago;
import com.grupo.allfym.ms.pagos.domain.ports.in.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class PagoService implements ActualizarPagoUseCase, AsignarVentaUseCase, BuscarMetodoPagoUseCase, EliminarPagoUseCase,
        GuardarPagoUseCase,ObtenerPagoUseCase, RemoverVentaUseCase {

    private final ActualizarPagoUseCase actualizarPagoUseCase;
    private final AsignarVentaUseCase asignarVentaUseCase;
    private final BuscarMetodoPagoUseCase buscarMetodoPagoUseCase;
    private final EliminarPagoUseCase eliminarPagoUseCase;
    private final GuardarPagoUseCase guardarPagoUseCase;
    private final ObtenerPagoUseCase obtenerPagoUseCase;
    private final RemoverVentaUseCase removerVentaUseCase;


    @Override
    public Optional<Pago> actualizarPago(Long id, Pago pago) {
        return actualizarPagoUseCase.actualizarPago(id,pago);
    }

    @Override
    public Optional<Venta> asignarVenta(Venta venta, Long id) {
        return asignarVentaUseCase.asignarVenta(venta,id);
    }

    @Override
    public List<Pago> buscarMetodoPago(String metodo) {
        return buscarMetodoPagoUseCase.buscarMetodoPago(metodo);
    }

    @Override
    public void eliminarPago(Long id) {
        eliminarPagoUseCase.eliminarPago(id);
    }

    @Override
    public Pago guardar(Pago pago) {
        return guardarPagoUseCase.guardar(pago);
    }

    @Override
    public Optional<Pago> obtenerPago(Long id) {
        return obtenerPagoUseCase.obtenerPago(id);
    }

    @Override
    public List<Pago> listaPago() {
        return obtenerPagoUseCase.listaPago();
    }

    @Override
    public Optional<Venta> removerVenta(Venta venta, Long id) {
        return removerVentaUseCase.removerVenta(venta,id);
    }
}

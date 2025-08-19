package com.grupo.allfym.ms.pagos.application.usecases;

import com.grupo.allfym.ms.pagos.domain.models.classes.Pago;
import com.grupo.allfym.ms.pagos.domain.ports.in.BuscarMetodoPagoUseCase;
import com.grupo.allfym.ms.pagos.domain.ports.out.PagoRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class BuscarMetodoPagoUseCaseImp implements BuscarMetodoPagoUseCase {
    private final PagoRepositoryPort pagoRepositoryPort;


    @Override
    public List<Pago> buscarMetodoPago(String metodo) {
        return pagoRepositoryPort.listaMetodoPago(metodo);
    }
}

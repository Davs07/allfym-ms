package com.grupo.allfym.ms.pagos.application.usecases;

import com.grupo.allfym.ms.pagos.domain.models.classes.Pago;
import com.grupo.allfym.ms.pagos.domain.ports.in.ActualizarPagoUseCase;
import com.grupo.allfym.ms.pagos.domain.ports.out.PagoRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class ActualizarPagoUseCaseImp implements ActualizarPagoUseCase {

    private final PagoRepositoryPort pagoRepositoryPort;

    @Override
    public Optional<Pago> actualizarPago(Long id, Pago pago) {
        return pagoRepositoryPort.actualizar(pago,id);
    }
}

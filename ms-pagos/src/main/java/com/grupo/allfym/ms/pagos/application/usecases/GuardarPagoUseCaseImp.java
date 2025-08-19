package com.grupo.allfym.ms.pagos.application.usecases;

import com.grupo.allfym.ms.pagos.domain.models.classes.Pago;
import com.grupo.allfym.ms.pagos.domain.ports.in.GuardarPagoUseCase;
import com.grupo.allfym.ms.pagos.domain.ports.out.PagoRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GuardarPagoUseCaseImp implements GuardarPagoUseCase {
    private final PagoRepositoryPort pagoRepositoryPort;

    @Override
    public Pago guardar(Pago pago) {
        return pagoRepositoryPort.guardar(pago);
    }
}

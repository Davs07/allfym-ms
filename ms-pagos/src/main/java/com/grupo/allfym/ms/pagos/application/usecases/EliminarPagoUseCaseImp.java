package com.grupo.allfym.ms.pagos.application.usecases;

import com.grupo.allfym.ms.pagos.domain.ports.in.EliminarPagoUseCase;
import com.grupo.allfym.ms.pagos.domain.ports.out.PagoRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EliminarPagoUseCaseImp implements EliminarPagoUseCase {
    private final PagoRepositoryPort pagoRepositoryPort;

    @Override
    public void eliminarPago(Long id) {
        pagoRepositoryPort.eliminar(id);
    }
}

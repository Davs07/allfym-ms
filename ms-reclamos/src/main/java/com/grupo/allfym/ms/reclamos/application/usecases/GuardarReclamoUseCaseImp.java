package com.grupo.allfym.ms.reclamos.application.usecases;

import com.grupo.allfym.ms.reclamos.domain.models.classes.Reclamo;
import com.grupo.allfym.ms.reclamos.domain.ports.in.GuardarReclamoUseCase;
import com.grupo.allfym.ms.reclamos.domain.ports.out.ReclamoRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GuardarReclamoUseCaseImp implements GuardarReclamoUseCase {
    private final ReclamoRepositoryPort repositoryPort;

    @Override
    public Reclamo guardar(Reclamo reclamo) {
        return repositoryPort.guardar(reclamo);
    }
}

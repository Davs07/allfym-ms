package com.grupo.allfym.ms.reclamos.application.usecases;

import com.grupo.allfym.ms.reclamos.domain.models.classes.Reclamo;
import com.grupo.allfym.ms.reclamos.domain.ports.in.ActualizarReclamoUseCase;
import com.grupo.allfym.ms.reclamos.domain.ports.out.ReclamoRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class ActualizarReclamoUseCaseImp implements ActualizarReclamoUseCase {
    private final ReclamoRepositoryPort repositoryPort;

    @Override
    public Optional<Reclamo> actualizarReclamo(Long id, Reclamo reclamo) {
        return repositoryPort.actualizar(id,reclamo);
    }
}

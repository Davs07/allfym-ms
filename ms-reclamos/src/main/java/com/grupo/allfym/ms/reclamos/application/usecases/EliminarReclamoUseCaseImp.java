package com.grupo.allfym.ms.reclamos.application.usecases;

import com.grupo.allfym.ms.reclamos.domain.ports.in.EliminarReclamoUseCase;
import com.grupo.allfym.ms.reclamos.domain.ports.out.ReclamoRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EliminarReclamoUseCaseImp implements EliminarReclamoUseCase {
    private final ReclamoRepositoryPort repositoryPort;


    @Override
    public void eliminarReclamo(Long id) {
        repositoryPort.eliminar(id);
    }
}

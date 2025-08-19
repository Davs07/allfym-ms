package com.grupo.allfym.ms.reclamos.domain.ports.in;

import com.grupo.allfym.ms.reclamos.domain.models.classes.Reclamo;

import java.util.Optional;

public interface ActualizarReclamoUseCase {
    Optional<Reclamo> actualizarReclamo(Long id, Reclamo reclamo);
}

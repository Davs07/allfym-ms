package com.grupo.allfym.ms.reclamos.domain.ports.in;

import com.grupo.allfym.ms.reclamos.domain.models.classes.Reclamo;

import java.util.List;
import java.util.Optional;

public interface ObtenerReclamoUseCase {
    List<Reclamo> listaReclamo();
    Optional<Reclamo> detalleReclamo(Long id);
}

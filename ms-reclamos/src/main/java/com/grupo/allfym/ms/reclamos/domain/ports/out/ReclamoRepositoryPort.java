package com.grupo.allfym.ms.reclamos.domain.ports.out;

import com.grupo.allfym.ms.reclamos.domain.models.classes.Reclamo;

import java.util.List;
import java.util.Optional;

public interface ReclamoRepositoryPort {
    List<Reclamo> listar();
    Optional<Reclamo> porId(Long id);
    Optional<Reclamo> actualizar(Long id, Reclamo reclamo);
    Reclamo guardar(Reclamo reclamo);
    void eliminar(Long id);
    Optional<Reclamo> cambiarEstado(String estado, Long id);
}

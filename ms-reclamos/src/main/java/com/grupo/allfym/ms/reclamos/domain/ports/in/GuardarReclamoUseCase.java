package com.grupo.allfym.ms.reclamos.domain.ports.in;

import com.grupo.allfym.ms.reclamos.domain.models.classes.Reclamo;

public interface GuardarReclamoUseCase {
    Reclamo guardar (Reclamo reclamo);
}

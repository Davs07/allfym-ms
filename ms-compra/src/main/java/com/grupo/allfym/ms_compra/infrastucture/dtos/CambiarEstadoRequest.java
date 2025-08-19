package com.grupo.allfym.ms_compra.infrastucture.dtos;

import com.grupo.allfym.ms_compra.domain.models.enums.Estado;
import lombok.Data;

@Data
public class CambiarEstadoRequest {
    private Estado estado;
}

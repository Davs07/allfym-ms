package com.grupo.allfym.ms.reclamos.domain.models.classes;

import com.grupo.allfym.ms.reclamos.domain.models.Cliente;
import com.grupo.allfym.ms.reclamos.domain.models.enums.EstadoReclamo;
import com.grupo.allfym.ms.reclamos.domain.models.vo.FechaReclamo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reclamo {
    private Long idReclamo;
    private String descripcion;
    private EstadoReclamo estadoReclamo;
    private FechaReclamo fechaReclamo;
    private ReclamoCliente reclamoCliente;
    private Cliente cliente;
}

package com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReclamoProveedorRequestDTO {
    private String descripcion;
    private Long idProveedor;
    private String nroOrden;
    private String estadoReclamo; // Opcional para actualizaciones
}

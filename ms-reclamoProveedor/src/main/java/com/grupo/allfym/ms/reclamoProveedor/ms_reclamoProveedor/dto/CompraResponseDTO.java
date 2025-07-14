package com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraResponseDTO {
    private Long id;
    private Long idProveedor;
    private Double total;
    private LocalDateTime fechaCompra;
}

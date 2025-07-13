package com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReclamoProveedorResponseDTO {
    private Long idReclamo;
    private String descripcion;
    private Long idProveedor;
    private String nombreProveedor;
    private String emailProveedor;
    private String nroOrden;
    private Long idCompra;
    private Double montoCompra;
    private String estadoReclamo;
    private LocalDateTime fechaCreacion;

    // Constructor sin información de proveedor/compra
    public ReclamoProveedorResponseDTO(Long idReclamo, String descripcion, Long idProveedor,
                                      String nroOrden, String estadoReclamo, LocalDateTime fechaCreacion) {
        this.idReclamo = idReclamo;
        this.descripcion = descripcion;
        this.idProveedor = idProveedor;
        this.nroOrden = nroOrden;
        this.estadoReclamo = estadoReclamo;
        this.fechaCreacion = fechaCreacion;
    }
}

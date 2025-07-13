package com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "ms-compra", url = "${microservices.compra.url:http://localhost:8030}")
public interface CompraCliente {

    @GetMapping("/api/compra/{id}")
    ResponseEntity<CompraResponseDTO> obtenerCompraPorId(@PathVariable("id") Long id);

    @GetMapping("/api/compra/proveedor/{idProveedor}")
    ResponseEntity<List<CompraResponseDTO>> obtenerComprasPorProveedor(@PathVariable("idProveedor") Long idProveedor);

    @GetMapping("/api/compra")
    ResponseEntity<List<CompraResponseDTO>> obtenerTodasLasCompras();
}

// DTO para la respuesta de compra
class CompraResponseDTO {
    private Long id;
    private Long idProveedor;
    private String nombreProveedor;
    private String rucProveedor;
    private String emailProveedor;
    private LocalDateTime fechaEmision;
    private LocalDateTime fechaRecepcion;
    private String estado;
    private Double montoTotal;
    private String observaciones;

    // Constructor por defecto
    public CompraResponseDTO() {}

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdProveedor() { return idProveedor; }
    public void setIdProveedor(Long idProveedor) { this.idProveedor = idProveedor; }

    public String getNombreProveedor() { return nombreProveedor; }
    public void setNombreProveedor(String nombreProveedor) { this.nombreProveedor = nombreProveedor; }

    public String getRucProveedor() { return rucProveedor; }
    public void setRucProveedor(String rucProveedor) { this.rucProveedor = rucProveedor; }

    public String getEmailProveedor() { return emailProveedor; }
    public void setEmailProveedor(String emailProveedor) { this.emailProveedor = emailProveedor; }

    public LocalDateTime getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDateTime fechaEmision) { this.fechaEmision = fechaEmision; }

    public LocalDateTime getFechaRecepcion() { return fechaRecepcion; }
    public void setFechaRecepcion(LocalDateTime fechaRecepcion) { this.fechaRecepcion = fechaRecepcion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Double getMontoTotal() { return montoTotal; }
    public void setMontoTotal(Double montoTotal) { this.montoTotal = montoTotal; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}

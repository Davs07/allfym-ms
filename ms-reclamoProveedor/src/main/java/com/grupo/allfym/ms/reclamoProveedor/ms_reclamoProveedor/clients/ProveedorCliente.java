package com.grupo.allfym.ms.reclamoProveedor.ms_reclamoProveedor.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-proveedores", url = "${microservices.proveedores.url:http://localhost:8010}")
public interface ProveedorCliente {

    @GetMapping("/api/proveedor/{id}")
    ResponseEntity<ProveedorResponseDTO> obtenerProveedorPorId(@PathVariable("id") Long id);

    @GetMapping("/api/proveedor")
    ResponseEntity<List<ProveedorResponseDTO>> obtenerTodosLosProveedores();

    @GetMapping("/api/proveedor/estado/{estado}")
    ResponseEntity<List<ProveedorResponseDTO>> obtenerProveedoresPorEstado(@PathVariable("estado") String estado);
}

// DTO para la respuesta del proveedor
class ProveedorResponseDTO {
    private Long id;
    private String nombre;
    private String ruc;
    private String telefono;
    private String email;
    private String calle;
    private String ciudad;
    private String codigoPostal;
    private String pais;
    private String estado;

    // Constructor por defecto
    public ProveedorResponseDTO() {}

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRuc() { return ruc; }
    public void setRuc(String ruc) { this.ruc = ruc; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = calle; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getCodigoPostal() { return codigoPostal; }
    public void setCodigoPostal(String codigoPostal) { this.codigoPostal = codigoPostal; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}

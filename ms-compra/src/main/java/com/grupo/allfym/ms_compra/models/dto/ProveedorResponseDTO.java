package com.grupo.allfym.ms_compra.models.dto;

import java.time.LocalDateTime;

public class ProveedorResponseDTO {

    private Long id;
    private String nombre;
    private String ruc;
    private String telefono;
    private String email;
    private String direccionCompleta;
    private String calle;
    private String ciudad;
    private String codigoPostal;
    private String pais;
    private String estado;
    private LocalDateTime fechaRegistro;

    public ProveedorResponseDTO() {
    }

    public ProveedorResponseDTO(Long id, String nombre, String ruc, String telefono, String email,
                               String calle, String ciudad, String codigoPostal, String pais,
                               String estado, LocalDateTime fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.ruc = ruc;
        this.telefono = telefono;
        this.email = email;
        this.calle = calle;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.pais = pais;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.direccionCompleta = String.format("%s, %s, %s, %s", calle, ciudad, codigoPostal, pais);
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccionCompleta() {
        return direccionCompleta;
    }

    public void setDireccionCompleta(String direccionCompleta) {
        this.direccionCompleta = direccionCompleta;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}

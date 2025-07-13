package com.grupo.allfym.ms_proveedores.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ProveedorRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Pattern(regexp = "^\\d{11}$", message = "El RUC debe tener exactamente 11 dígitos")
    private String ruc;

    @Pattern(regexp = "^[+]?[0-9]{8,15}$", message = "El teléfono debe tener entre 8 y 15 dígitos")
    private String telefono;

    @Email(message = "El formato del email no es válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    private String calle;
    private String ciudad;
    private String codigoPostal;
    private String pais;

    public ProveedorRequestDTO() {
    }

    public ProveedorRequestDTO(String nombre, String ruc, String telefono, String email,
                              String calle, String ciudad, String codigoPostal, String pais) {
        this.nombre = nombre;
        this.ruc = ruc;
        this.telefono = telefono;
        this.email = email;
        this.calle = calle;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.pais = pais;
    }

    // Getters y Setters
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
}

package com.grupo.allfym.ms.ventas.models;

import java.time.LocalDateTime;

public class ClienteResponseDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String nombreCompleto;
    private String email;
    private String dni;
    private String telefono;
    private String direccion;
    private LocalDateTime fechaRegistro;
    private Boolean activo;

    public ClienteResponseDTO() {
    }

    public ClienteResponseDTO(Long id, String nombre, String apellido, String email, String dni,
                              String telefono, String direccion, LocalDateTime fechaRegistro, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreCompleto = nombre + " " + apellido;
        this.email = email;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
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
        this.nombreCompleto = nombre + " " + apellido;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
        this.nombreCompleto = nombre + " " + apellido;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}

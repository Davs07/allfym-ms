package com.grupo.allfym.ms.ventas.domain.models;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Modelo que representa un cliente externo al dominio de ventas.
 * Este modelo refleja la información necesaria del microservicio de clientes.
 */
public class Cliente {
    
    private final Long id;
    private final String nombre;
    private final String apellido;
    private final String nombreCompleto;
    private final String email;
    private final String dni;
    private final String telefono;
    private final String direccion;
    private final LocalDateTime fechaRegistro;
    private final Boolean activo;
    
    public Cliente(Long id, String nombre, String apellido, String nombreCompleto, 
                   String email, String dni, String telefono, String direccion, 
                   LocalDateTime fechaRegistro, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getDni() {
        return dni;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }
    
    public Boolean getActivo() {
        return activo;
    }
    
    public boolean estaActivo() {
        return activo != null && activo;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return String.format("Cliente{id=%d, nombreCompleto='%s', activo=%s}", 
                           id, nombreCompleto, activo);
    }
}

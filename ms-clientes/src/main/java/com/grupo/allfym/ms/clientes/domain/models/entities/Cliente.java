package com.grupo.allfym.ms.clientes.domain.models.entities;

import com.grupo.allfym.ms.clientes.domain.models.enums.EstadoCliente;
import com.grupo.allfym.ms.clientes.domain.models.ov.EmailAddress;
import com.grupo.allfym.ms.clientes.domain.models.ov.FechaRegistro;
import com.grupo.allfym.ms.clientes.domain.models.ov.Telefono;

import java.util.Objects;

public class Cliente {
    
    private final Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private EmailAddress email;
    private Telefono telefono;
    private String direccion;
    private final FechaRegistro fechaRegistro;
    private EstadoCliente estado;

    // Constructor para nueva entidad (sin ID)
    public Cliente(String nombre, String apellido, String dni, EmailAddress email, 
                   Telefono telefono, String direccion) {
        this.id = null;
        this.nombre = validarNombre(nombre);
        this.apellido = validarApellido(apellido);
        this.dni = validarDni(dni);
        this.email = Objects.requireNonNull(email, "El email no puede ser nulo");
        this.telefono = Objects.requireNonNull(telefono, "El teléfono no puede ser nulo");
        this.direccion = direccion;
        this.fechaRegistro = new FechaRegistro();
        this.estado = EstadoCliente.ACTIVO;
    }

    // Constructor para entidad existente (con ID) - Permite nulos para datos de BD
    public Cliente(Long id, String nombre, String apellido, String dni, EmailAddress email,
                   Telefono telefono, String direccion, FechaRegistro fechaRegistro, EstadoCliente estado) {
        this.id = id;
        this.nombre = nombre; // Permitir nulos
        this.apellido = apellido; // Permitir nulos
        this.dni = dni; // Permitir nulos
        this.email = email; // Permitir nulos
        this.telefono = telefono; // Permitir nulos
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro; // Permitir nulos
        this.estado = estado; // Permitir nulos
    }

    // Métodos de negocio
    public void cambiarEmail(EmailAddress nuevoEmail) {
        this.email = Objects.requireNonNull(nuevoEmail, "El nuevo email no puede ser nulo");
    }

    public void cambiarTelefono(Telefono nuevoTelefono) {
        this.telefono = Objects.requireNonNull(nuevoTelefono, "El nuevo teléfono no puede ser nulo");
    }

    public void actualizarDatos(String nombre, String apellido, String direccion) {
        this.nombre = validarNombre(nombre);
        this.apellido = validarApellido(apellido);
        this.direccion = direccion;
    }

    public void activar() {
        if (this.estado == EstadoCliente.SUSPENDIDO || this.estado == EstadoCliente.INACTIVO) {
            this.estado = EstadoCliente.ACTIVO;
        } else {
            throw new IllegalStateException("No se puede activar un cliente en estado: " + this.estado);
        }
    }

    public void desactivar() {
        if (this.estado == EstadoCliente.ACTIVO) {
            this.estado = EstadoCliente.INACTIVO;
        } else {
            throw new IllegalStateException("Solo se pueden desactivar clientes activos");
        }
    }

    public void suspender() {
        if (this.estado == EstadoCliente.ACTIVO) {
            this.estado = EstadoCliente.SUSPENDIDO;
        } else {
            throw new IllegalStateException("Solo se pueden suspender clientes activos");
        }
    }

    public boolean estaActivo() {
        return estado.equals(EstadoCliente.ACTIVO);
    }

    public boolean puedeRealizarOperaciones() {
        return estado.equals(EstadoCliente.ACTIVO);
    }

    // Validaciones privadas
    private String validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        return nombre.trim();
    }

    private String validarApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        }
        return apellido.trim();
    }

    private String validarDni(String dni) {
        if (dni == null || !dni.matches("^\\d{8}$")) {
            throw new IllegalArgumentException("El DNI debe tener exactamente 8 dígitos");
        }
        return dni;
    }

    // Getters (solo lectura para preservar inmutabilidad)
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public EmailAddress getEmail() {
        return email;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public FechaRegistro getFechaRegistro() {
        return fechaRegistro;
    }

    public EstadoCliente getEstado() {
        return estado;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
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
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", email=" + email +
                ", estado=" + estado +
                '}';
    }
}

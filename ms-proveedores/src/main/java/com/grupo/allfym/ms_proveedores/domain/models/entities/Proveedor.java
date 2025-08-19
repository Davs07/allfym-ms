package com.grupo.allfym.ms_proveedores.domain.models.entities;

import com.grupo.allfym.ms_proveedores.domain.models.enums.Estado;
import com.grupo.allfym.ms_proveedores.domain.models.vo.Direccion;
import com.grupo.allfym.ms_proveedores.domain.models.vo.Email;
import com.grupo.allfym.ms_proveedores.domain.models.vo.FechaRegistro;
import com.grupo.allfym.ms_proveedores.domain.models.vo.Telefono;

public class Proveedor {

    private final Long id;
    private final String nombre;
    private final Telefono telefono;
    private final String ruc;
    private final Email email;
    private final Direccion direccion;
    private final Estado estado;
    private final FechaRegistro fechaDeRegistro;

    public Proveedor(Long id, String nombre, String ruc, Telefono telefono, Email email, Direccion direccion, Estado estado, FechaRegistro fechaDeRegistro) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.id = id;
        this.nombre = nombre;
        this.ruc = ruc;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.estado = (estado != null) ? estado : Estado.ACTIVO;
        this.fechaDeRegistro = (fechaDeRegistro != null) ? fechaDeRegistro : new FechaRegistro();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public String getRuc() {
        return ruc;
    }

    public Email getEmail() {
        return email;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public Estado getEstado() {
        return estado;
    }

    public FechaRegistro getFechaDeRegistro() {
        return fechaDeRegistro;
    }



}
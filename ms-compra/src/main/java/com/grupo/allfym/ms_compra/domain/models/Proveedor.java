package com.grupo.allfym.ms_compra.domain.models;

public class Proveedor {
    private final Long id;
    private final String nombre;

    public Proveedor(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
}

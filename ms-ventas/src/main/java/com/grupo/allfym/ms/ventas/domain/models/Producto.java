package com.grupo.allfym.ms.ventas.domain.models;

import java.util.Objects;

public class Producto {
    
    private final Long idProducto;
    private final String nombre;
    private final String descripcion;
    private final Double precio;
    private final String categoria;
    private final String marca;
    
    public Producto(Long idProducto, String nombre, String descripcion, 
                    Double precio, String categoria, String marca) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.marca = marca;
    }
    
    public Long getIdProducto() {
        return idProducto;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public Double getPrecio() {
        return precio;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public String getMarca() {
        return marca;
    }
    
    public boolean tieneInformacionCompleta() {
        return nombre != null && !nombre.trim().isEmpty() && 
               precio != null && precio > 0;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(idProducto, producto.idProducto);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(idProducto);
    }
    
    @Override
    public String toString() {
        return String.format("Producto{id=%d, nombre='%s', precio=%.2f}", 
                           idProducto, nombre, precio);
    }
}

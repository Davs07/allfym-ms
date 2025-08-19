package com.grupo.allfym.ms_compra.domain.models.entities;

public class DetalleCompra {
    private final Long id;
    private final Long idProducto;
    private final Integer cantidad;
    private final Double precioCompra;
    private final Double subtotal;

    public DetalleCompra(Long id, Long idProducto, Integer cantidad, Double precioCompra) {
        if (idProducto == null) {
            throw new IllegalArgumentException("El ID del producto no puede ser nulo");
        }
        if (cantidad == null || cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
        if (precioCompra == null || precioCompra <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero");
        }

        this.id = id;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
        this.subtotal = cantidad * precioCompra;
    }

    public Long getId() {
        return id;
    }
    public Long getIdProducto() {
        return idProducto;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public Double getPrecioCompra() {
        return precioCompra;
    }
    public Double getSubtotal() {
        return subtotal;
    }
}


package com.grupo.allfym.ms.ventas.domain.ports.out;

public interface AlmacenServicePort {
    
    void reducirStock(Long productoId, int cantidad);

    boolean hayStockSuficiente(Long productoId, int cantidad);

    void restaurarStock(Long productoId, int cantidad);
}

package com.grupo.allfym.ms.ventas.infrastructure.adapters;

import com.grupo.allfym.ms.ventas.domain.ports.out.AlmacenServicePort;
import com.grupo.allfym.ms.ventas.infrastructure.clients.AlmacenClient;
import org.springframework.stereotype.Component;

@Component
public class AlmacenServiceAdapter implements AlmacenServicePort {

    private final AlmacenClient almacenClient;

    public AlmacenServiceAdapter(AlmacenClient almacenClient) {
        this.almacenClient = almacenClient;
    }

    @Override
    public void reducirStock(Long productoId, int cantidad) {
        try {
            almacenClient.reducirStock(productoId, cantidad);
        } catch (Exception e) {
            System.err.println("Error al reducir stock: " + e.getMessage());
            throw new RuntimeException("No se pudo reducir el stock del producto: " + productoId, e);
        }
    }

    @Override
    public boolean hayStockSuficiente(Long productoId, int cantidad) {
        try {
            // Para simplificar siempre hay stock disponible
            return true;
        } catch (Exception e) {
            System.err.println("Error al verificar stock: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void restaurarStock(Long productoId, int cantidad) {
        try {
            // En un caso real ería un endpoint diferente para aumentar stock
            System.out.println("Restaurando stock para producto: " + productoId + ", cantidad: " + cantidad);
        } catch (Exception e) {
            System.err.println("Error al restaurar stock: " + e.getMessage());
            throw new RuntimeException("No se pudo restaurar el stock del producto: " + productoId, e);
        }
    }
}

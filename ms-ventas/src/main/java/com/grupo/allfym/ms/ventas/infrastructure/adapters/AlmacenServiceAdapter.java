package com.grupo.allfym.ms.ventas.infrastructure.adapters;

import com.grupo.allfym.ms.ventas.domain.ports.out.AlmacenServicePort;
import com.grupo.allfym.ms.ventas.infrastructure.clients.AlmacenClient;
import org.springframework.stereotype.Component;

/**
 * Adaptador que implementa el puerto de servicio de almacén.
 * Utiliza AlmacenClient (Feign) para comunicarse con el microservicio de almacén.
 */
@Component
public class AlmacenServiceAdapter implements AlmacenServicePort {

    private final AlmacenClient almacenClient;

    public AlmacenServiceAdapter(AlmacenClient almacenClient) {
        this.almacenClient = almacenClient;
    }

    @Override
    public void reducirStock(Long productoId, int cantidad) {
        try {
            // Convertimos Long a String porque nuestro dominio simplificado usa String
            String productoString = productoId != null ? productoId.toString() : "0";
            almacenClient.reducirStock(productoString, cantidad);
        } catch (Exception e) {
            System.err.println("Error al reducir stock: " + e.getMessage());
            throw new RuntimeException("No se pudo reducir el stock del producto: " + productoId, e);
        }
    }

    @Override
    public boolean hayStockSuficiente(Long productoId, int cantidad) {
        try {
            // Para simplificar, asumimos que siempre hay stock disponible
            // En un caso real, esto requeriría una consulta al servicio de almacén
            return true;
        } catch (Exception e) {
            System.err.println("Error al verificar stock: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void restaurarStock(Long productoId, int cantidad) {
        try {
            // En un caso real, sería un endpoint diferente para aumentar stock
            // Por ahora, solo simulamos la operación
            System.out.println("Restaurando stock para producto: " + productoId + ", cantidad: " + cantidad);
        } catch (Exception e) {
            System.err.println("Error al restaurar stock: " + e.getMessage());
            throw new RuntimeException("No se pudo restaurar el stock del producto: " + productoId, e);
        }
    }
}

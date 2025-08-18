package com.grupo.allfym.ms.ventas.infrastructure.adapters;

import com.grupo.allfym.ms.ventas.domain.models.Producto;
import com.grupo.allfym.ms.ventas.domain.ports.out.ProductoServicePort;
import com.grupo.allfym.ms.ventas.infrastructure.clients.ProductoClient;
import com.grupo.allfym.ms.ventas.infrastructure.dtos.ProductoDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Adaptador que implementa el puerto de servicio de productos.
 * Utiliza ProductoClient (Feign) para comunicarse con el microservicio de productos.
 */
@Component
public class ProductoServiceAdapter implements ProductoServicePort {

    private final ProductoClient productoClient;

    public ProductoServiceAdapter(ProductoClient productoClient) {
        this.productoClient = productoClient;
    }

    @Override
    public Optional<Producto> buscarPorId(Long productoId) {
        try {
            var response = productoClient.obtenerProductoPorId(productoId);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                ProductoDto productoDto = response.getBody();
                Producto producto = convertirADominio(productoDto);
                return Optional.of(producto);
            }
            return Optional.empty();
        } catch (Exception e) {
            System.err.println("Error al buscar producto: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public boolean existeYEsValido(Long productoId) {
        try {
            var response = productoClient.obtenerProductoPorId(productoId);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                ProductoDto producto = response.getBody();
                return producto.isActivo() && 
                       producto.getNombre() != null && 
                       !producto.getNombre().trim().isEmpty() &&
                       producto.getPrecio() != null && 
                       producto.getPrecio().compareTo(java.math.BigDecimal.ZERO) > 0;
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error al verificar producto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Convierte ProductoDto a modelo de dominio.
     */
    private Producto convertirADominio(ProductoDto dto) {
        return new Producto(
            dto.getIdProducto(),
            dto.getNombre(),
            dto.getDescripcion(),
            dto.getPrecio() != null ? dto.getPrecio().doubleValue() : 0.0,
            dto.getCategoria(),
            dto.getMarca()
        );
    }
}

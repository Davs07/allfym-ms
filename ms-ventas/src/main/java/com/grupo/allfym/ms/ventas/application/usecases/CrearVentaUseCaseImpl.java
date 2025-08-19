package com.grupo.allfym.ms.ventas.application.usecases;

import com.grupo.allfym.ms.ventas.domain.models.entities.Venta;
import com.grupo.allfym.ms.ventas.domain.models.entities.DetalleVenta;
import com.grupo.allfym.ms.ventas.domain.ports.in.CrearVentaUseCase;
import com.grupo.allfym.ms.ventas.domain.ports.out.VentaRepositoryPort;
import com.grupo.allfym.ms.ventas.domain.ports.out.ClienteServicePort;
import com.grupo.allfym.ms.ventas.domain.ports.out.ProductoServicePort;
import com.grupo.allfym.ms.ventas.domain.ports.out.AlmacenServicePort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CrearVentaUseCaseImpl implements CrearVentaUseCase {

    private final VentaRepositoryPort ventaRepository;
    private final ClienteServicePort clienteService;
    private final AlmacenServicePort almacenService;

    public CrearVentaUseCaseImpl(VentaRepositoryPort ventaRepository,
                                ClienteServicePort clienteService,
                                AlmacenServicePort almacenService) {
        this.ventaRepository = ventaRepository;
        this.clienteService = clienteService;
        this.almacenService = almacenService;
    }

    @Override
    public Venta crear(Venta venta) {
        // Validamos que el cliente existe y está activo
        if (venta == null || venta.getClienteId() == null) {
            throw new IllegalArgumentException("La venta y el clienteId son requeridos");
        }
        if (!clienteService.existeYEstaActivo(venta.getClienteId())) {
            throw new IllegalArgumentException("Cliente no encontrado o inactivo: " + venta.getClienteId());
        }

        // Validamos y procesar detalles de la venta (reducir stock)
        if (venta.getDetalles() == null || venta.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("La venta debe tener al menos un detalle");
        }
        for (DetalleVenta detalle : venta.getDetalles()) {
            if (detalle.getProductoId() == null) {
                throw new IllegalArgumentException("El ID de producto es requerido");
            }
            if (detalle.getCantidad() == null || detalle.getCantidad() <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
            }
            if (detalle.getPrecioUnitario() == null || detalle.getPrecioUnitario().compareTo(java.math.BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("El precio unitario debe ser mayor a cero");
            }
            // Reducimos el stock
            almacenService.reducirStock(detalle.getProductoId(), detalle.getCantidad());
        }

        // Recalculamos el total por seguridad
        venta.calcularTotal();

        // Guardamos la venta
        return ventaRepository.guardar(venta);
    }
}

package com.grupo.allfym.ms.ventas.application.usecases;

import com.grupo.allfym.ms.ventas.domain.models.entities.Venta;
import com.grupo.allfym.ms.ventas.domain.models.entities.DetalleVenta;
import com.grupo.allfym.ms.ventas.domain.models.enums.MetodoPago;
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
    private final ProductoServicePort productoService;
    private final AlmacenServicePort almacenService;

    public CrearVentaUseCaseImpl(VentaRepositoryPort ventaRepository,
                                ClienteServicePort clienteService,
                                ProductoServicePort productoService,
                                AlmacenServicePort almacenService) {
        this.ventaRepository = ventaRepository;
        this.clienteService = clienteService;
        this.productoService = productoService;
        this.almacenService = almacenService;
    }

    @Override
    public Venta crear(CrearVentaCommand command) {
        // 1. Validar que el cliente existe y está activo
        if (!clienteService.existeYEstaActivo(command.clienteId())) {
            throw new IllegalArgumentException("Cliente no encontrado o inactivo: " + command.clienteId());
        }

        // 2. Crear venta
        MetodoPago metodoPago = MetodoPago.valueOf(command.metodoPago());
        Venta venta = new Venta(command.clienteId(), metodoPago);

        // 3. Validar y procesar detalles de la venta
        for (DetalleVentaCommand detalleCmd : command.detalles()) {
            // Validación básica del productoId
            if (detalleCmd.productoId() == null) {
                throw new IllegalArgumentException("El ID de producto es requerido");
            }

            // Hacer que se reduzca el stock usando almacenService
            almacenService.reducirStock(detalleCmd.productoId(), detalleCmd.cantidad());

            // Crear detalle de venta
            DetalleVenta detalle = new DetalleVenta(
                detalleCmd.productoId(),
                detalleCmd.cantidad(),
                detalleCmd.precioUnitario()
            );
            venta.agregarDetalle(detalle);
        }

        // 4. Guardar la venta
        Venta ventaGuardada = ventaRepository.guardar(venta);

        // 5. Para simplificar, no reducimos stock automáticamente
        // En un caso real, aquí se podría reducir el stock de productos

        return ventaGuardada;
    }
}

package com.grupo.allfym.ms_compra.application.usecases;

import com.grupo.allfym.ms.compra.domain.models.Producto;
import com.grupo.allfym.ms.compra.domain.models.Proveedor;
import com.grupo.allfym.ms.compra.domain.models.entities.Compra;
import com.grupo.allfym.ms.compra.domain.models.entities.DetalleCompra;
import com.grupo.allfym.ms.compra.domain.ports.in.GuardarCompraUseCase;
import com.grupo.allfym.ms.compra.domain.ports.out.AlmacenServicePort;
import com.grupo.allfym.ms.compra.domain.ports.out.CompraRepositoryPort;
import com.grupo.allfym.ms.compra.domain.ports.out.ProveedorServicePort;

import java.util.List;
import java.util.Optional;

public class GuardarCompraUseCaseImpl implements GuardarCompraUseCase {
    private final CompraRepositoryPort compraRepositoryPort;
    private final ProveedorServicePort proveedorServicePort;
    private final AlmacenServicePort almacenServicePort;

    public GuardarCompraUseCaseImpl(CompraRepositoryPort compraRepositoryPort,
                                    ProveedorServicePort proveedorServicePort,
                                    AlmacenServicePort almacenServicePort) {
        this.compraRepositoryPort = compraRepositoryPort;
        this.proveedorServicePort = proveedorServicePort;
        this.almacenServicePort = almacenServicePort;
    }

    @Override
    public Compra guardar(Compra compra) {
        Optional<Proveedor> proveedor = proveedorServicePort.buscarPorId(compra.getIdProveedor());
        if (proveedor.isEmpty()) {
            throw new IllegalArgumentException("Proveedor no encontrado con ID: " + compra.getIdProveedor());
        }

        List<Producto> productosAlmacen = almacenServicePort.obtenerProductos();
        for (DetalleCompra detalle : compra.getDetalles()) {
            boolean productoExiste = productosAlmacen.stream()
                    .anyMatch(p -> p.getId().equals(detalle.getIdProducto()));

            if (!productoExiste) {
                throw new IllegalArgumentException("El producto ID " + detalle.getIdProducto() + " no existe en almacén.");
            }

            try {
                almacenServicePort.aumentarStock(detalle.getIdProducto(), detalle.getCantidad());
            } catch (Exception e) {
                System.out.println("Error al aumentar el stock para producto ID " + detalle.getIdProducto() + ": " + e.getMessage());
            }
        }

        return compraRepositoryPort.guardar(compra);
    }
}

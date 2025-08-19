package com.grupo.allfym.ms_compra.application.usecases;

import com.grupo.allfym.ms_compra.domain.models.Producto;
import com.grupo.allfym.ms_compra.domain.models.entities.Compra;
import com.grupo.allfym.ms_compra.domain.models.entities.DetalleCompra;
import com.grupo.allfym.ms_compra.domain.ports.in.AgregarDetalleCompraUseCase;
import com.grupo.allfym.ms_compra.domain.ports.out.AlmacenServicePort;
import com.grupo.allfym.ms_compra.domain.ports.out.CompraRepositoryPort;

import java.util.List;
import java.util.Optional;

public class AgregarDetalleCompraUseCaseImpl implements AgregarDetalleCompraUseCase {
    private final CompraRepositoryPort compraRepositoryPort;
    private final AlmacenServicePort almacenServicePort;

    public AgregarDetalleCompraUseCaseImpl(CompraRepositoryPort compraRepositoryPort,
                                           AlmacenServicePort almacenServicePort) {
        this.compraRepositoryPort = compraRepositoryPort;
        this.almacenServicePort = almacenServicePort;
    }

    @Override
    public Compra agregarDetalle(Long idCompra, DetalleCompra detalleCompra) {
        Optional<Compra> compraOpt = compraRepositoryPort.buscarPorId(idCompra);
        if (compraOpt.isEmpty()) {
            throw new IllegalArgumentException("No se encontró la compra con ID: " + idCompra);
        }

        List<Producto> productosAlmacen = almacenServicePort.obtenerProductos();
        boolean productoExiste = productosAlmacen.stream()
                .anyMatch(p -> p.getId().equals(detalleCompra.getIdProducto()));

        if (!productoExiste) {
            throw new IllegalArgumentException("El producto ID " + detalleCompra.getIdProducto() + " no existe en almacén.");
        }

        // para aumenter el stock en almacen
        try {
            almacenServicePort.aumentarStock(detalleCompra.getIdProducto(), detalleCompra.getCantidad());
        } catch (Exception e) {
            System.out.println("Error al aumentar el stock para producto ID " + detalleCompra.getIdProducto() + ": " + e.getMessage());
        }

        Compra compraActualizada = compraOpt.get().agregarDetalle(detalleCompra);
        return compraRepositoryPort.guardar(compraActualizada);
    }
}

package com.grupo.allfym.ms.almacen.application.usecases.almacenproducto;

import com.grupo.allfym.ms.almacen.application.usecases.movimientos.CreateMovimientoUseCaseImpl;
import com.grupo.allfym.ms.almacen.domain.models.AlmacenProducto;
import com.grupo.allfym.ms.almacen.domain.ports.in.almacenproducto.AumentarStockAlmacenProductoUseCase;
import com.grupo.allfym.ms.almacen.domain.ports.out.AlmacenProductoRepositoryPort;
import com.grupo.allfym.ms.almacen.domain.ports.out.ExternalServicePort;
import com.grupo.allfym.ms.almacen.infrastructure.entities.TipoMovimiento;

public class AumentarStockAlmacenProductoUseCaseImpl implements AumentarStockAlmacenProductoUseCase {

    private final AlmacenProductoRepositoryPort almacenProductoRepositoryPort;
    private final ExternalServicePort externalServicePort;
    private final CreateMovimientoUseCaseImpl createMovimientoUseCase;

    public AumentarStockAlmacenProductoUseCaseImpl(AlmacenProductoRepositoryPort almacenProductoRepositoryPort, ExternalServicePort externalServicePort, CreateMovimientoUseCaseImpl createMovimientoUseCase) {
        this.almacenProductoRepositoryPort = almacenProductoRepositoryPort;
        this.externalServicePort = externalServicePort;
        this.createMovimientoUseCase = createMovimientoUseCase;
    }

    @Override
    public AlmacenProducto aumentarStock(Long idAlmacen, Integer cantidad) {

        AlmacenProducto almacenProducto = almacenProductoRepositoryPort.findByIdAlmacen(idAlmacen)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en almacén"));

        almacenProducto.setStock(almacenProducto.getStock() + cantidad);

        // Crear el movimiento y agregarlo al almacenProducto
        createMovimientoUseCase.createMovimiento(almacenProducto, TipoMovimiento.ENTRADA, cantidad);

        // Guardar el almacenProducto con el movimiento incluido
        return almacenProductoRepositoryPort.save(almacenProducto);
    }
}

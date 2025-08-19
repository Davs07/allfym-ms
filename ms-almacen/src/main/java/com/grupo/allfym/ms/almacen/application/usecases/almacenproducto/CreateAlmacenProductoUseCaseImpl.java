package com.grupo.allfym.ms.almacen.application.usecases.almacenproducto;

import com.grupo.allfym.ms.almacen.application.usecases.movimientos.CreateMovimientoUseCaseImpl;
import com.grupo.allfym.ms.almacen.domain.models.AlmacenProducto;
import com.grupo.allfym.ms.almacen.domain.ports.in.almacenproducto.CreateAlmacenProductoUseCase;
import com.grupo.allfym.ms.almacen.domain.ports.out.AlmacenProductoRepositoryPort;
import com.grupo.allfym.ms.almacen.domain.ports.out.ExternalServicePort;
import com.grupo.allfym.ms.almacen.infrastructure.entities.TipoMovimiento;
import com.grupo.allfym.ms.almacen.infrastructure.entities.pojo.Producto;

public class CreateAlmacenProductoUseCaseImpl implements CreateAlmacenProductoUseCase {

    private final AlmacenProductoRepositoryPort almacenProductoRepositoryPort;
    private final CreateMovimientoUseCaseImpl createMovimientoUseCase;
    private final ExternalServicePort externalServicePort;

    public CreateAlmacenProductoUseCaseImpl(AlmacenProductoRepositoryPort almacenProductoRepositoryPort, CreateMovimientoUseCaseImpl createMovimientoUseCase, ExternalServicePort externalServicePort) {
        this.almacenProductoRepositoryPort = almacenProductoRepositoryPort;
        this.createMovimientoUseCase = createMovimientoUseCase;
        this.externalServicePort = externalServicePort;
    }

    private Producto validarProductoExiste(Long idProducto) {
        try {
            return externalServicePort.getProductoById(idProducto);
        } catch (Exception e) {
            throw new RuntimeException("Producto no encontrado en el catálogo: " + idProducto);
        }
    }

    @Override
    public AlmacenProducto createAlmacenProducto(Long idProducto, Integer stockInicial) throws Exception {
        externalServicePort.getProductoById(idProducto); // Valida existencia

        AlmacenProducto almacenProducto = new AlmacenProducto();
        almacenProducto.setIdProducto(idProducto);
        almacenProducto.setStock(stockInicial);

        // Crear movimiento inicial
        createMovimientoUseCase.createMovimiento(almacenProducto, TipoMovimiento.ENTRADA, stockInicial);

        return almacenProductoRepositoryPort.save(almacenProducto);
    }



}

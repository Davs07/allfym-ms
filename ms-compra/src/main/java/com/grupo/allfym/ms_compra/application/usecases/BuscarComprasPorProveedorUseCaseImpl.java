package com.grupo.allfym.ms_compra.application.usecases;

import com.grupo.allfym.ms_compra.domain.models.Proveedor;
import com.grupo.allfym.ms_compra.domain.models.entities.Compra;
import com.grupo.allfym.ms_compra.domain.ports.in.BuscarComprasPorProveedorUseCase;
import com.grupo.allfym.ms_compra.domain.ports.out.CompraRepositoryPort;
import com.grupo.allfym.ms_compra.domain.ports.out.ProveedorServicePort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BuscarComprasPorProveedorUseCaseImpl implements BuscarComprasPorProveedorUseCase {
    private final CompraRepositoryPort compraRepositoryPort;
    private final ProveedorServicePort proveedorServicePort;

    public BuscarComprasPorProveedorUseCaseImpl(CompraRepositoryPort compraRepositoryPort,
                                                ProveedorServicePort proveedorServicePort) {
        this.compraRepositoryPort = compraRepositoryPort;
        this.proveedorServicePort = proveedorServicePort;
    }

    @Override
    public List<Compra> buscarPorIdProveedor(Long idProveedor) {
        Optional<Proveedor> proveedor = proveedorServicePort.buscarPorId(idProveedor);
        if (proveedor.isEmpty()) {
            throw new IllegalArgumentException("No se encontró el proveedor");
        }
        return compraRepositoryPort.buscarPorIdProveedor(idProveedor);
    }

    @Override
    public List<Compra> buscarPorNombreProveedor(String nombre) {
        List<Proveedor> proveedores = proveedorServicePort.listar();
        List<Long> ids = new ArrayList<>();

        for (Proveedor p : proveedores) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                ids.add(p.getId());
            }
        }

        List<Compra> resultado = new ArrayList<>();
        for (Long id : ids) {
            resultado.addAll(compraRepositoryPort.buscarPorIdProveedor(id));
        }

        return resultado;
    }
}

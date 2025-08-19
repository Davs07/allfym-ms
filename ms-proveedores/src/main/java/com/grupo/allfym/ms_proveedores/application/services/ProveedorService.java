package com.grupo.allfym.ms_proveedores.application.services;

import com.grupo.allfym.ms_proveedores.domain.models.entities.Proveedor;
import com.grupo.allfym.ms_proveedores.domain.models.enums.Estado;
import com.grupo.allfym.ms_proveedores.domain.ports.in.*;

import java.util.List;
import java.util.Optional;

public class ProveedorService implements BuscarProveedorPorIdUseCase, EliminarProveedorUseCase,
        GuardarProveedorUseCase, ListarProveedoresPorEstadoUseCase,
        ListarProveedoresUseCase, ActualizarProveedorPorIdUseCase {
    private final BuscarProveedorPorIdUseCase buscarProveedorPorIdUseCase;
    private final EliminarProveedorUseCase eliminarProveedorUseCase;
    private final GuardarProveedorUseCase guardarProveedorUseCase;
    private final ListarProveedoresPorEstadoUseCase listarProveedoresPorEstadoUseCase;
    private final ListarProveedoresUseCase listarProveedoresUseCase;
    private final ActualizarProveedorPorIdUseCase actualizarProveedorPorIdUseCase;


    public ProveedorService(BuscarProveedorPorIdUseCase buscarProveedorPorIdUseCase,
                            EliminarProveedorUseCase eliminarProveedorUseCase,
                            GuardarProveedorUseCase guardarProveedorUseCase,
                            ListarProveedoresPorEstadoUseCase listarProveedoresPorEstadoUseCase,
                            ListarProveedoresUseCase listarProveedoresUseCase,
                            ActualizarProveedorPorIdUseCase actualizarProveedorPorIdUseCase) {
        this.buscarProveedorPorIdUseCase = buscarProveedorPorIdUseCase;
        this.eliminarProveedorUseCase = eliminarProveedorUseCase;
        this.guardarProveedorUseCase = guardarProveedorUseCase;
        this.listarProveedoresPorEstadoUseCase = listarProveedoresPorEstadoUseCase;
        this.listarProveedoresUseCase = listarProveedoresUseCase;
        this.actualizarProveedorPorIdUseCase = actualizarProveedorPorIdUseCase;

    }

    @Override
    public Optional<Proveedor> buscarPorId(Long id) {
        return buscarProveedorPorIdUseCase.buscarPorId(id);
    }

    @Override
    public void eliminar(Long id) {
        eliminarProveedorUseCase.eliminar(id);
    }

    @Override
    public Proveedor guardar(Proveedor proveedor) {
        return guardarProveedorUseCase.guardar(proveedor);
    }

    @Override
    public List<Proveedor> listarPorEstado(Estado estado) {
        return listarProveedoresPorEstadoUseCase.listarPorEstado(estado);
    }

    @Override
    public List<Proveedor> listar() {
        return listarProveedoresUseCase.listar();
    }

    @Override
    public Proveedor actualizar(Long id, Proveedor proveedor) {
        return actualizarProveedorPorIdUseCase.actualizar(id, proveedor);
    }
}
